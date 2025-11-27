<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Créer un élève</title>
</head>
<body>
<h1>Créer un élève</h1>
<form action="eleve" method="post">
  Nom: <input type="text" name="nom" required><br>
  Prénom: <input type="text" name="prenom" required><br>
  Filière:
  <select name="filiereId">
    <c:forEach var="f" items="${filieres}">
      <option value="${f.id}">${f.nom}</option>
    </c:forEach>
  </select><br>
  <input type="submit" value="Créer">
</form>
<a href="eleve?action=list">Retour à la liste</a>
</body>
</html>
