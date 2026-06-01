function confirmerSuppression(event) {
    if (!confirm("Voulez-vous vraiment supprimer cette étape ?")) {
        event.preventDefault();
        return false;
    }
    return true;
}
