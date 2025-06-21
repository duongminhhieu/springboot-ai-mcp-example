db = db.getSiblingDB('product-service');

if (!db.products.findOne()) {
    const data = cat('/docker-entrypoint-initdb.d/product-service.products.json');
    const products = JSON.parse(data);
    db.products.insertMany(products);
    print("Inserted sample products into 'product-service.products'");
} else {
    print("'products' collection already contains data. Skipping insertion.");
}
