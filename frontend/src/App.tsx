import {RouterProvider} from "react-router-dom";
import {router} from "./Router.tsx";

function App() {

    return (
        <div className="bg-gray-800 font-rubik">
            <RouterProvider router={router}/>
        </div>
    )
}

export default App
