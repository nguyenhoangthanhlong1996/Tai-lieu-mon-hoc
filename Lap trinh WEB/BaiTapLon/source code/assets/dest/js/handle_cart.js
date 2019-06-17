function addToCart(id, quantity) {
    var cart = $.cookie('cart');
    if (!cart) {
        cart = [];
        cart.push({id: id, quantity: quantity});
        $.cookie('cart', JSON.stringify(cart));
    } else {
        cart = JSON.parse($.cookie('cart'));
        var index = cart.findIndex(o => o.id === id);
        if (index !== -1) {
            cart[index].quantity += quantity;
        } else {
            cart.push({id: id, quantity: quantity});
        }
        $.cookie('cart', JSON.stringify(cart));
    }
    updateCart();
}

function updateCart() {
    var cart = JSON.parse($.cookie('cart'));
    if (cart) {
        var totalQuantity = 0;
        $.each(cart, function (index, p) {
            console.log(p);
            totalQuantity += parseInt(p.quantity);
        });
        $('#header_cart_total_quantity').text(totalQuantity);
    }
}
