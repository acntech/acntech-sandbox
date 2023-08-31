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

class EventClient {
    #url = null;
    #eventSource = null;
    onConnect = (event) => {
    }
    onDisconnect = () => {
    }
    onMessage = (event) => {
    };
    onError = (event) => {
    };

    constructor(url) {
        this.#url = url;
    }

    activate() {
        if (!this.#eventSource) {
            this.#eventSource = new EventSource(this.#url);
            this.#eventSource.addEventListener("open", this.onConnect, false);
            this.#eventSource.addEventListener("message", this.onMessage, false);
            this.#eventSource.addEventListener("error", this.onError, false);
        } else if (this.#eventSource.readyState === 0) {
            console.warn("EventSource is already connecting");
        } else if (this.#eventSource.readyState === 1) {
            console.warn("EventSource is already open");
        }
    }

    deactivate() {
        if (!this.#eventSource) {
            console.error("EventSource is null");
        } else if (this.#eventSource.readyState === 2) {
            console.warn("EventSource is already closed");
        } else {
            this.#eventSource.close();
            this.#eventSource = null;
            this.onDisconnect();
        }
    }

    state() {
        if (!!this.#eventSource) {
            return this.#eventSource.readyState;
        } else {
            return undefined;
        }
    }

    isConnected() {
        return this.#eventSource?.readyState === 1;
    }
}

$(() => {
    onDisconnected();

    const client = new EventClient("/greetings");

    client.onConnect = (event) => {
        onConnected();
        console.log('Connected', event)
    };

    client.onDisconnect = () => {
        onDisconnected();
        console.log('Disconnected')
    }

    client.onMessage = (event) => {
        count++;

        if (event.data) {
            console.log('Received message with data ' + event.data);
            const data = JSON.parse(event.data);
            $('#messages-list').prepend(`<li>${count}: ${data.message}</li>`);
        } else {
            console.log('Received message without data');
            $('#messages-list').prepend('<li></li>');
        }
    };

    client.onError = (event) => {
        console.log('Error', event);
    };

    $('#connect-button').click(() => {
        console.log('State', client.state());
        if (client.isConnected()) {
            console.log('Disconnecting')
            client.deactivate();
        } else {
            console.log('Connecting')
            client.activate();
        }
    });
});