const receiveMessage = (message) => {
    if (message.body) {
        console.log('Received STOMP message with body ' + message.body);
        const body = JSON.parse(message.body);
        $('#messages').prepend('<li>' + body.message + '</li>');
    } else {
        console.log('Received STOMP message without body');
        $('#messages').prepend('<li></li>');
    }
}
$(() => {
    $('#name').prop('disabled', true);
    $('#publish').prop('disabled', true);

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
        $('#connect').prop('disabled', true);
        $('#name').prop('disabled', false);
        $('#publish').prop('disabled', false);
        console.log('Connected', frame)
        const subscription = client.subscribe('/topic/greeting', receiveMessage);
        console.log('Subscribing', subscription)
    };

    client.onStompError = (frame) => {
        console.log('Broker reported error: ' + frame.headers['message']);
        console.log('Additional details: ' + frame.body);
    };

    $('#connect').click(() => {
        console.log('Connecting')
        client.activate();
    });

    $('#publish').click(() => {
        const name = $('#name').val();
        const message = {name: name};
        const body = JSON.stringify(message);
        console.log('Sending STOMP message with body ' + body);
        client.publish({
            destination: '/app/hello',
            body: body
        });
    });
});