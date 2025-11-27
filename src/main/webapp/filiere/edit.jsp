<h2>Modifier une filière</h2>

<form action="../filieres" method="post">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${filiere.id}"/>

    Code : <input type="text" name="code" value="${filiere.code}"/><br/>
    Libellé : <input type="text" name="libelle" value="${filiere.libelle}"/><br/>

    <button type="submit">Mettre à jour</button>
</form>
