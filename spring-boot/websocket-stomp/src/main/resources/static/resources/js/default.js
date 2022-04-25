let count = 0;

const receiveMessage = (message) => {
    count++;

    if (message.body) {
        console.log('Received STOMP message with body ' + message.body);
        const body = JSON.parse(message.body);
        $('#messages-list').prepend(`<li>${count}: ${body.message}</li>`);
    } else {
        console.log('Received STOMP message without body');
        $('#messages-list').prepend('<li></li>');
    }
}

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

$(() => {
    onDisconnected();

    const client = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/ws',
        connectHeaders: {},
        debug: (str) => {
            //console.log(str);
        },
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });

    client.onConnect = (frame) => {
        onConnected();
        console.log('Connected', frame)
        const subscription = client.subscribe('/topic/greetings', receiveMessage);
        console.log('Subscribing', subscription)
    };

    client.onDisconnect = (frame) => {
        onDisconnected();
        console.log('Disconnected', frame)
    }

    client.onStompError = (frame) => {
        console.log('Broker reported error: ' + frame.headers['message']);
        console.log('Additional details: ' + frame.body);
    };

    $('#connect-button').click(() => {
        if (client.connected) {
            console.log('Disconnecting')
            client.deactivate();
        } else {
            console.log('Connecting')
            client.activate();
        }
    });

    $('#publish-button').click(() => {
        const name = $('#name-input').val();
        const message = {name: name};
        const body = JSON.stringify(message);
        console.log('Sending STOMP message with body ' + body);
        client.publish({
            destination: '/app/hello',
            body: body
        });
        $('#name-input').val('');
    });
});