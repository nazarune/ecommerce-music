import Header from "./Header/Header.tsx";
import Footer from "./Footer/Footer.tsx";

function Layout({children}: { children: React.ReactNode }) {
    return (
        <div className="min-h-svh flex flex-col">
            <Header/>
            <main className="grow container mx-auto px-4 max-w-7xl bg-red-100">{children}</main>
            <Footer/>
        </div>
    )
}

export default Layout