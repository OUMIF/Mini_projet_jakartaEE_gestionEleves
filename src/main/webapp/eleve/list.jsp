<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Liste des élèves</title>
</head>
<body>
<h1>Liste des élèves</h1>
<a href="eleve?action=create">Ajouter un élève</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Nom</th>
    <th>Prénom</th>
    <th>Filière</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="e" items="${eleves}">
    <tr>
      <td>${e.id}</td>
      <td>${e.nom}</td>
      <td>${e.prenom}</td>
      <td>${e.filiere.nom}</td>
      <td>
        <a href="eleve?action=delete&id=${e.id}">Supprimer</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
