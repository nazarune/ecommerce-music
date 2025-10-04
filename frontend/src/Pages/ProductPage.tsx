import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import type {Product} from "../Components/ProductCard/ProductCard.tsx";
import axios from "axios";

function ProductPage() {
    const params = useParams()
    
    const [product, setProduct] = useState<Product>();

    useEffect(() => {
        axios.get<Product>('api/products/' + params.id)
            .then(res =>
            setProduct(res.data))
            .catch(err =>
            console.error('Error fetching a product: ' + err))
    }, [params.id]);

    return (
        <div className="p-4">
            <div className="grid grid-cols-2 gap-4">
                <div id="productCover">
                    <img className="rounded-lg" src="https://placehold.co/1000x1000"/>
                </div>
                <div>
                    <div className="text-4xl">{product?.name}</div>
                    <div className="text-2xl">{product?.price}</div>
                </div>
            </div>
        </div>
    )
}

export default ProductPage;