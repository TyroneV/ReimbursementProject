//console.log(window.location.pathname);
const userInfo = document.getElementById("userInfo");
const container = document.getElementById("container");
const ul = document.getElementById("navbarUl");


if (getSession()) {
  loggedIn();
}else{
    logout();
}

function reactBootleg() {
  const uri = window.location.pathname;

  ul.innerHTML = "";
  let li1 = generateNav("/reimbursement/login.page", "Login");
  let li2 = generateNav("/reimbursement/register.page", "Register");
  if (
    (uri == "/reimbursement/" && !getSession()) ||
    (uri == "/reimbursement/login.page" && !getSession())
  ) {
    li1 = navActive(true, li1);
    li2 = navActive(false, li2);
    ul.append(li1);
    ul.append(li2);
  } else if (uri == "/reimbursement/register.page" && !getSession()) {
    li1 = navActive(false, li1);
    li2 = navActive(true, li2);
    ul.append(li1);
    ul.append(li2);
    container.innerHTML =
      "        <br>\n" +
      '        <p class="text-danger font-weight-bold" id="loginFailed"></p>\n' +
      '        <form id="registerForm">\n' +
      '            <div class="form-group">\n' +
      '              <label for="inputUsername">Username</label>\n' +
      '              <input type="text" class="form-control" id="inputUsername" placeholder="Enter your Username" required>\n' +
      "            </div>\n" +
      '            <div class="form-group">\n' +
      '              <label for="inputPassword">Password</label>\n' +
      '              <input type="password" class="form-control" id="inputPassword" placeholder="Enter your Password" required>\n' +
      "            </div>\n" +
      '            <div class="form-group">\n' +
      '                <label for="inputPassword">Confirm Password</label>\n' +
      '                <input type="password" class="form-control" id="confirmPassword" placeholder="Enter confirm your Password" required>\n' +
      "            </div>\n" +
      '            <div class="form-group">\n' +
      '                <label for="inputUsername">First Name</label>\n' +
      '                <input type="text" class="form-control" id="inputFirstName" placeholder="Enter your First Name" required>\n' +
      "            </div>\n" +
      '            <div class="form-group">\n' +
      '                <label for="inputUsername">Last Name</label>\n' +
      '                <input type="text" class="form-control" id="inputLastName" placeholder="Enter your Last Name" required>\n' +
      "            </div>\n" +
      '            <div class="form-group">\n' +
      '                <label for="inputUsername">Email</label>\n' +
      '                <input type="email" class="form-control" id="inputEmail" placeholder="Enter your Email" required>\n' +
      "            </div>\n" +
      '            <label for="select">Role</label>\n' +
      '            <select class="form-control" id="select">\n' +
      '                <option value="1">Finance Manager</option>\n' +
      '                <option value="2">Employee</option>\n' +
      "            </select>\n" +
      "            <br>\n" +
      '            <button type="submit" class="btn btn-secondary" id="submitReg">Submit</button>\n' +
      "        </form>\n";
      document.getElementById("registerForm").addEventListener("submit", register);
  } else if (getSession()) {
    if (uri != "/reimbursement/home.page") {
      window.location.pathname = "/reimbursement/home.page";
    }
    container.innerHTML = "";
    fetchTableData();
    ticketForm();
  }
}

reactBootleg();






