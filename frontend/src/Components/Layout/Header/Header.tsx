function Header() {
    return (
        <header className="p-4 bg-rose-950 shadow">
            <div className="container mx-auto px-4 max-w-7xl">
                <div className="flex justify-between items-center p-2">
                    <a className="text-white font-bold text-2xl" href="/">3sharp</a>
                    <div className="space-x-4">
                        <a className="text-white hover:text-gray-300" href="#">Home</a>
                        <a className="text-white hover:text-gray-300" href="#">About</a>
                        <a className="text-white hover:text-gray-300" href="#">Contact</a>
                    </div>
                </div>
            </div>
        </header>
    )
}

export default Header