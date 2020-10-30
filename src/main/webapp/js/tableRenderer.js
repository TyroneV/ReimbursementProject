function renderTable(fm, reimbursementTable, fv) {
  const container = document.getElementById("tableHolder");
  container.innerText = "";
  const table = document.createElement("table");
  table.setAttribute("class", "table");
  const tableHead = document.createElement("thead");
  const trow = document.createElement("tr");
  const rows = [
    "ID",
    "Amount",
    "Submitted",
    "Resolved",
    "Description",
    "Author",
    "Resolver",
    "Status",
    "Type",
  ];
  const filter = document.createElement("select");
  filter.setAttribute("id", "filter");
  filter.setAttribute("class", "form-control");
  const fOption = document.createElement("option");
  const fOption2 = document.createElement("option");
  const fOption3 = document.createElement("option");
  const fOption4 = document.createElement("option");
  rows.forEach((element) => {
    const th = document.createElement("th");
    th.setAttribute("scope", "col");
    th.innerText = element;
    trow.append(th);
  });
  if (fm) {
    fOption.setAttribute("value", 0);
    fOption.innerText = "No filter";
    fOption2.setAttribute("value", 1);
    fOption2.innerText = "Pending";
    fOption3.setAttribute("value", 2);
    fOption3.innerText = "Approved";
    fOption4.setAttribute("value", 3);
    fOption4.innerText = "Denied";
    filter.append(fOption, fOption2, fOption3, fOption4);
    filter.value = fv;
    const th = document.createElement("th");
    th.setAttribute("scope", "col");
    th.append(filter);
    trow.append(th);
  }
  filter.addEventListener("change", () => {
    fetchTableData(filter.value);
  });
  tableHead.append(trow);
  table.append(tableHead);
  const tableBody = document.createElement("tbody");
  for (const reimbursement of reimbursementTable) {
    const tr = document.createElement("tr");
    const idTd = document.createElement("td");
    const amountTd = document.createElement("td");
    const submittedTd = document.createElement("td");
    const resolveTd = document.createElement("td");
    const descriptionTd = document.createElement("td");
    const authorTd = document.createElement("td");
    const resolverTd = document.createElement("td");
    const statusTd = document.createElement("td");
    const typeTd = document.createElement("td");
    const select = document.createElement("select");

    select.setAttribute("class", "btn btn-secondary");
    select.setAttribute("id", "statusSelect");

    idTd.innerHTML = reimbursement.id;
    amountTd.innerHTML = "$" + reimbursement.amount.toFixed(2);
    if (reimbursement.submittedDate) {
      submittedTd.innerHTML = new Date(reimbursement.submittedDate);
    }
    if (reimbursement.resolvedDate) {
      resolveTd.innerHTML = new Date(reimbursement.resolvedDate);
    }
    descriptionTd.innerHTML = reimbursement.description;
    authorTd.innerHTML = reimbursement.author.username;
    if (reimbursement.resolver) {
      resolverTd.innerHTML = reimbursement.resolver.username;
    }
    if (fm) {
      const option = document.createElement("option");
      const option2 = document.createElement("option");
      const option3 = document.createElement("option");
      option.setAttribute("value", 1);
      option.innerText = "Pending";
      if (reimbursement.status.id == 1) {
        option.selected = true;
      } else if (reimbursement.status.id == 2) {
        option2.selected = true;
      } else if (reimbursement.status.id == 3) {
        option3.selected = true;
      }
      select.append(option);

      option2.setAttribute("value", 2);
      option2.innerText = "Approved";

      select.append(option2);

      option3.setAttribute("value", 3);
      option3.innerText = "Denied";

      select.append(option3);
      select.setAttribute("id", "sel" + reimbursement.id);
      statusTd.append(select);
    } else {
      statusTd.innerHTML = reimbursement.status.status;
    }
    typeTd.innerHTML = reimbursement.type.type;
    tr.append(
      idTd,
      amountTd,
      submittedTd,
      resolveTd,
      descriptionTd,
      authorTd,
      resolverTd,
      statusTd,
      typeTd
    );
    if (fm) {
      const submitTd = document.createElement("td");
      const submit = document.createElement("button");
      submit.setAttribute("class", "btn btn-secondary");
      submit.innerText = "Update Status";
      submit.addEventListener("click", () => {
        let ticket = {
          id: reimbursement.id,
          status: {
            id: select.value,
          },
        };
        updateTicket(ticket);
      });
      submitTd.append(submit);
      tr.append(submitTd);
    }
    tableBody.append(tr);
  }

  table.append(tableBody);
  container.append(table);
}

async function updateTicket(ticket) {
  const dropDown = document.getElementById("sel" + ticket.id);
  dropDown.setAttribute("class", "btn btn-secondary");
  const updated = await fetch(
    baseUrl + window.location.host + "/reimbursement/api/ticket/update.json",
    {
      method: "post",
      body: JSON.stringify(ticket),
    }
  );
  if (updated.ok) {
    dropDown.setAttribute("class", "btn btn-success");
  }
}

async function fetchTableData(filterV = 0) {
  let route;
  if (filterV == 0) {
    route = "/reimbursement/api/ticket/all.json";
  } else {
    route = "/reimbursement/api/ticket/filter.json?status=" + filterV;
  }
  let stringUrl = baseUrl + window.location.host + route;
  try {
    const allTable = await fetch(stringUrl, {
      method: "get",
    });
    if (allTable.ok) {
      const json = await allTable.json();
      renderTable(true, json, filterV);
    }
  } catch (error) {
    console.log(error);
  }
  try {
    const userTable = await fetch(
      baseUrl + window.location.host + "/reimbursement/api/ticket/past.json",
      {
        method: "get",
      }
    );
    if (userTable.ok) {
      const json = await userTable.json();
      renderTable(false, json, filterV);
    }
  } catch (e) {
    console.log(e);
  }
}
