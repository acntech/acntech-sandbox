let count = 0;

const onConnected = () => {
    $('#connect-button').html('Disconnect!').removeClass('btn-primary').addClass('btn-success');
    $('#name-input').prop('disabled', false);
    $('#publish-button').prop('disabled', false).removeClass('btn-secondary').addClass('btn-primary');
    $('#publish-container').removeClass("text-secondary");
    $('#messages-container').removeClass("text-secondary");
}

const onDisconnected = () => {
    $('#connect-button').html('Connect!').removeClass('btn-success').addClass('btn-primary');
    $('#name-input').prop('disabled', true).val('');
    $('#publish-button').prop('disabled', true).removeClass('btn-primary').addClass('btn-secondary');
    $('#publish-container').addClass("text-secondary");
    $('#messages-container').addClass("text-secondary");
}

class PollClient {
    #url = null;
    #connected = false;
    onConnect = () => {
    }
    onDisconnect = () => {
    }
    onMessage = (message) => {
    };
    onError = (error) => {
    };

    constructor(url) {
        this.#url = url;
    }

    async poll() {
        const response = await fetch(this.#url);
        if (response.status === 200) {
            const body = await response.json();
            this.onMessage(body);
        } else if (response.status === 204) {
            this.onMessage(null);
        } else if (response.status >= 400 && response.status <= 500) {
            this.onError(`Error: ${response.status} - ${response.statusText}`)
        } else {
            console.warn(`Unhandled status: ${response.status} - ${response.statusText}`);
        }
        if (this.#connected) {
            await this.poll();
        }
    }

    activate() {
        this.#connected = true;
        this.onConnect();
        this.poll();
    }

    deactivate() {
        this.#connected = false;
        this.onDisconnect();
    }

    isConnected() {
        return this.#connected;
    }
}

$(() => {
    onDisconnected();

    const client = new PollClient("/greetings");

    client.onConnect = () => {
        onConnected();
        console.log('Connected')
    };

    client.onDisconnect = () => {
        onDisconnected();
        console.log('Disconnected')
    }

    client.onMessage = (body) => {
        count++;

        if (body) {
            console.log('Received message', body);
            $('#messages-list').prepend(`<li>${count}: ${body.message}</li>`);
        } else {
            console.log('Received empty message');
            $('#messages-list').prepend(`<li>${count}:</li>`);
        }
    };

    client.onError = (error) => {
        console.log('Error', error);
    };

    $('#connect-button').click(() => {
        if (client.isConnected()) {
            console.log('Disconnecting')
            client.deactivate();
        } else {
            console.log('Connecting')
            client.activate();
        }
    });
});