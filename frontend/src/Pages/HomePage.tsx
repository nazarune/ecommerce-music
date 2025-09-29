import Layout from "../Components/Layout/Layout.tsx";
import HeroSection from "../Components/HeroSection/HeroSection.tsx";
import ProductSection from "../Components/ProductSection/ProductSection.tsx";

function HomePage() {
    return (
        <>
            <Layout>
                <HeroSection/>
                <ProductSection title="trending"/>
                <ProductSection title="pre-order now!"/>
            </Layout>
        </>
    )
}

export default HomePage;