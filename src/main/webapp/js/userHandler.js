const baseUrl = "http://";

async function login(event) {
  event.preventDefault();
  const form = event.currentTarget;
  const user = {
    username: form[0].value,
    password: form[1].value,
  };
  try {
    const success = await fetch(
      baseUrl + window.location.host + "/reimbursement/api/login.json",
      {
        method: "post",
        body: JSON.stringify(user),
      }
    );
    if (success.ok) {
      errorElem(false, "Login Successful!");
      setSession(user.username);
      window.location.pathname = "/reimbursement/home.page";
      loggedIn();
    } else {
      throw new Error("Login Failed!");
    }
  } catch (error) {
    errorElem(true, "Login Failed!");
  }
}

async function register(event) {
  event.preventDefault();
  const form = event.currentTarget;

  if (form[1].value === form[2].value) {
    const user = {
      username: form[0].value,
      password: form[1].value,
      firstName: form[3].value,
      lastName: form[4].value,
      email: form[5].value,
      userRole: {
        id: form[6].value,
      },
    };
    try {
      const success = await fetch(
        baseUrl + window.location.host + "/reimbursement/api/register.json",
        {
          method: "post",
          body: JSON.stringify(user),
        }
      );
      if (success.ok) {
        errorElem(false, "Registration is Successful!");
      } else {
        errorElem(
          true,
          "Registration is Failed! Username or Email already exist!"
        );
        throw new Error("Registration Failed!");
      }
    } catch (err) {
      console.log(err);
    }
  } else {
    errorElem(true, "Registration Failed! Password does not match!");
  }
}

async function logout() {
  try {
    const success = await fetch(
      baseUrl + window.location.host + "/reimbursement/api/logout.json",
      {
        method: "get",
      }
    );
    if (success.ok) {
      setSession();
      console.log("Logged out!");
      window.location.pathname = "/reimbursement/login.page";
    } else {
      userInfo.innerHTML = "";
      setSession();
      throw new Error("Not logged in!");
    }
  } catch (err) {
    console.log(err);
  }
}

function loggedIn() {
  userInfo.innerHTML = "";
  const currentUser = document.createElement("p");
  currentUser.setAttribute("class", "text-light");
  currentUser.innerText = getSession();
  const logoutBtn = document.createElement("button");
  logoutBtn.setAttribute("type", "button");
  logoutBtn.setAttribute("class", "btn btn-outline-light");
  logoutBtn.setAttribute("id", "logoutBtn");
  logoutBtn.innerText = "Logout";
  userInfo.append(currentUser, logoutBtn);
  if (document.getElementById("logoutBtn")) {
    document.getElementById("logoutBtn").addEventListener("click", logout);
  }
}

function errorElem(failed, string) {
  const errorTxt = document.getElementById("loginFailed");
  if (failed) {
    errorTxt.setAttribute("class", "text-danger font-weight-bold");
  } else {
    errorTxt.setAttribute("class", "text-success font-weight-bold");
  }
  errorTxt.innerText = string;
}

function setSession(username) {
  if (username) {
    window.sessionStorage.setItem("username", username);
  } else {
    window.sessionStorage.setItem("username", "");
  }
}
function getSession() {
  return window.sessionStorage.getItem("username");
}

if (document.getElementById("loginForm")) {
  document.getElementById("loginForm").addEventListener("submit", login);
}

