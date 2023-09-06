import React, {FC, ReactElement} from "react";
import {Container} from "react-bootstrap";

const About: FC = (): ReactElement => {
    return (
        <Container>
            <div className="px-3 py-5 rounded-3 bg-light">
                <h2>This is the about page!</h2>
                <p>That&apos;s it :)</p>
            </div>
        </Container>
    );
};

export default About;