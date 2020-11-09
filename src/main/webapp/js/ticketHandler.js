function ticketForm() {
  const con = document.getElementById("container");
  con.innerHTML = "";
  const br = document.createElement("br");
  const form = document.createElement("form");
  form.className = "form-group";
  const div1 = document.createElement("div");
  div1.className = "form-group";
  const div2 = document.createElement("div");
  div2.className = "form-group";
  const div3 = document.createElement("div");
  div3.className = "form-group";
  const div4 = document.createElement("div");
  div4.className = "form-group";

  const label1 = document.createElement("label");
  label1.setAttribute("for", "amountId");
  const input1 = document.createElement("input");
  input1.className = "form-control";
  input1.type = "number";
  input1.id = "amountId";
  input1.placeholder = "Enter Amount of Money";
  input1.required = true;

  const label2 = document.createElement("label");
  label2.setAttribute("for", "descriptionId");
  const input2 = document.createElement("input");
  input2.className = "form-control";
  input2.type = "text";
  input2.id = "descriptionId";
  input2.placeholder = "Enter Description";
  input2.required = true;

  const label3 = document.createElement("label");
  label3.setAttribute("for", "typeId");
  const input3 = document.createElement("select");
  input3.className = "form-control";
  input3.id = "typeId";
  const ops = ["Lodging", "Travel", "Food", "Other"];
  let counter = 1;
  ops.forEach((element) => {
    const op = document.createElement("option");
    op.innerText = element;
    op.value = counter;
    counter++;
    input3.append(op);
  });
  const submitBtn = document.createElement("button");
  submitBtn.type = "submit";
  submitBtn.className = "btn btn-secondary";
  submitBtn.innerText = "Submit Reimbursement";

  div1.append(label1, input1);
  div2.append(label2, input2);
  div3.append(label3, input3);
  div4.append(submitBtn);
  form.append(br, div1, div2, div3, div4);
  con.append(form);
  form.addEventListener("submit", submitReim);
}

async function submitReim(event) {
  event.preventDefault();
  const subButton = event.currentTarget[3];
  const ticket = {
    amount: event.currentTarget[0].value,
    description: event.currentTarget[1].value,
    type: {
      id: event.currentTarget[2].value,
    },
  };
  try {
    subButton.disabled = true;
    const updated = await fetch(
      baseUrl + window.location.host + "/reimbursement/api/ticket/add.json",
      {
        method: "post",
        body: JSON.stringify(ticket),
      }
    );
    if (updated.ok) {
      fetchTableData();
      subButton.removeAttribute("disabled");
    }
  } catch (error) {
    console.log(error);
  }
}
