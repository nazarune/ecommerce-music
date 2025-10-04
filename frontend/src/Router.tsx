import {createBrowserRouter} from "react-router-dom";
import Layout from "./Components/Layout/Layout.tsx";
import HomePage from "./Pages/HomePage.tsx";
import ProductPage from "./Pages/ProductPage.tsx";

export const router = createBrowserRouter([
    {
        Component: Layout,
        children: [
            { index: true, Component: HomePage },
            {
                path: "product",
                children: [
                    { path: ":id", Component: ProductPage }
                ]
            }
        ]
    }
])