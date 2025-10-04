import ProductCard, {type Product} from "../ProductCard/ProductCard.tsx";
import {useEffect, useState} from "react";
import axios from "axios";

interface ProdSecProps {
    title: string
}

function ProductSection({title} : ProdSecProps) {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        axios.get<Product[]>('/api/products')
            .then(res =>
            setProducts(res.data.slice(0,5)))
            .catch(err =>
            console.error('Error fetching products:', err))
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