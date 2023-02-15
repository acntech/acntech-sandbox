import React, {FC, ReactElement} from "react";
import {Container} from "react-bootstrap";

const Home: FC = (): ReactElement => {
    return (
        <Container>
            <div className="px-3 py-5 rounded-3 bg-light">
                <h2>Welcome to this Spring Boot example!</h2>
                <p>This example shows an OIDC client login for a React frontend with a Spring REST API</p>
            </div>
        </Container>
    );
};

export default Home;