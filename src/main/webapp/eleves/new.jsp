<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Créer un élève</h2>

<form action="eleves" method="post">
    <input type="hidden" name="action" value="create"/>

    Nom : <input type="text" name="nom"/><br>
    Prénom : <input type="text" name="prenom"/><br>

    Filière :
    <select name="filiereId">
        <c:forEach items="${filieres}" var="f">
            <option value="${f.id}">${f.nom}</option>
        </c:forEach>
    </select>
    <br><br>

    <button type="submit">Créer</button>
</form>
