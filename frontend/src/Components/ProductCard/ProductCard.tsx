import {Link} from "react-router-dom";

export interface Product {
    id: number;
    name: string;
    price: number;
    description: string;
}

function ProductCard({product}: { product: Product }) {
    return (
        <Link to={"product/" + product.id} className="w-1/5">
            <div className="grid grid-cols-1 gap-2">
                <img className="rounded-lg" src="https://placehold.co/250x250"/>
                <a className="w-50 text-lg text-wrap">{product.name}</a>
                <a>{product.price}</a>
            </div>
        </Link>
    )
}

export default ProductCard;