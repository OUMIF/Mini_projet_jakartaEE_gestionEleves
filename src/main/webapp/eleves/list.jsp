<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Liste des élèves</h2>

<a href="eleves?action=new">Ajouter un élève</a>

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
        <a href="eleves?action=edit&id=${e.id}">Modifier</a>
        <a href="eleves?action=delete&id=${e.id}"
           onclick="return confirm('Supprimer ?')">Supprimer</a>
      </td>
    </tr>
  </c:forEach>
</table>
