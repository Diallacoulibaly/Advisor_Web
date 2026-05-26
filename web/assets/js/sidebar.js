const onglets = document.querySelectorAll('.onglet-section');

onglets.forEach((onglet) => {

    onglet.addEventListener('click', () => {

        onglets.forEach((o) => {
            o.classList.remove('active');
        });

        onglet.classList.add('active');

    });

});