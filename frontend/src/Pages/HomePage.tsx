import HeroSection from "../Components/HeroSection/HeroSection.tsx";
import ProductSection from "../Components/ProductSection/ProductSection.tsx";

function HomePage() {
    return (
        <>
            <HeroSection/>
            <ProductSection title="trending"/>
            <ProductSection title="pre-order now!"/>
        </>
    )
}

export default HomePage;