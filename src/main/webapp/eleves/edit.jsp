<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Modifier un élève</h2>

<form action="eleves" method="post">
  <input type="hidden" name="action" value="update"/>
  <input type="hidden" name="id" value="${eleve.id}"/>

  Nom :
  <input type="text" name="nom" value="${eleve.nom}"/><br>

  Prénom :
  <input type="text" name="prenom" value="${eleve.prenom}"/><br>

  Filière :
  <select name="filiereId">
    <c:forEach items="${filieres}" var="f">
      <option value="${f.id}" ${f.id == eleve.filiere.id ? "selected" : ""}>
          ${f.nom}
      </option>
    </c:forEach>
  </select>
  <br><br>

  <button type="submit">Mettre à jour</button>
</form>
