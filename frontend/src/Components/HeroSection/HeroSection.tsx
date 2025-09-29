function HeroSection() {
    return (
        <div className="p-4">
            <div className="relative">
                <img className="object-cover rounded-lg" src="https://placehold.co/1280x720"/>
                <div className="absolute top-3/5 bg-gray-800 opacity-20">
                    <a className="text-gray-200">Pre-order!</a>
                </div>
            </div>
        </div>
    )
}

export default HeroSection;