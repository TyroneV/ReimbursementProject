function navActive(active, li) {
  if (active) {
    li.setAttribute("class", "nav-item active");
  } else {
    li.setAttribute("class", "nav-item");
  }
  return li;
}
function generateNav(ref, inText) {
  const li = document.createElement("li");
  const a = document.createElement("a");
  a.setAttribute("href", ref);
  a.setAttribute("class", "nav-link");
  a.innerText = inText;
  li.append(a);
  return li;
}
