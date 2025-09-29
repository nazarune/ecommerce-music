import ProductCard, {type Product} from "../ProductCard/ProductCard.tsx";
import {useEffect, useState} from "react";

interface ProdSecProps {
    title: string
}

function ProductSection({title} : ProdSecProps) {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        // TODO: fetch products from API
        const fetchedProducts: Product[] = [
            {id: 1, name: "Vintage Vinyl", price: "25.99"},
            {id: 2, name: "Modern CD", price: "14.50"},
            {id: 3, name: "Classic Cassette", price: "9.99"},
            {id: 4, name: "Test", price:"5.00"},
            {id: 5, name: "SOFT ERROR", price:"29.99"}
        ];
        setProducts(fetchedProducts);
    }, []);

    return (
        <div className="p-4">
            <a className="text-xl">{title}</a>
            <div className="flex flex-row pt-4 gap-8">
                {products.map(product => (
                    <ProductCard key={product.id} product={product}/>
                ))}
            </div>
        </div>
    )
}

export default ProductSection;