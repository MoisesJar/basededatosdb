// Selecciona todas las instancias de los contenedores de productos y botones de siguiente y previo
const productContainers = [...document.querySelectorAll('.product-container')];
const nxtBtn = [...document.querySelectorAll('.nxt-btn')];
const preBtn = [...document.querySelectorAll('.pre-btn')];

// Itera sobre cada contenedor de productos
productContainers.forEach((item, i) => {
    
    // Obtiene las dimensiones del contenedor actual
    let containerDimensions = item.getBoundingClientRect();
    
    // Obtiene el ancho del contenedor
    let containerWidth = containerDimensions.width;

    // Añade un event listener al botón "siguiente" (nxt-btn) correspondiente
    nxtBtn[i].addEventListener('click', () => {
        // Cuando se hace clic, desplaza el contenedor hacia la derecha por el ancho completo del contenedor
        item.scrollLeft += containerWidth;
    })

    // Añade un event listener al botón "previo" (pre-btn) correspondiente
    preBtn[i].addEventListener('click', () => {
        // Cuando se hace clic, desplaza el contenedor hacia la izquierda por el ancho completo del contenedor
        item.scrollLeft -= containerWidth;
    })
})
