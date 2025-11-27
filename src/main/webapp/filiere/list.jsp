<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Liste des filières</h2>

<a href="filieres?action=add">Ajouter une filière</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Libellé</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="f" items="${filieres}">
        <tr>
            <td>${f.id}</td>
            <td>${f.code}</td>
            <td>${f.libelle}</td>
            <td>
                <a href="filieres?action=edit&id=${f.id}">Modifier</a>
                <form action="filieres" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${f.id}"/>
                    <button type="submit">Supprimer</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
