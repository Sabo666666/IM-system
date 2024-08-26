#include "tcp_lib.h"

// 创建一个Socket对象
QTcpSocket* createSocket() {
    return new QTcpSocket();
}

// 绑定Socket到指定的IP和端口
bool bindSocket(QTcpServer *server, const QString &ip, quint16 port) {
    return server->listen(QHostAddress(ip), port);
}

// 服务器端监听连接请求
bool listenForConnections(QTcpServer *server, int backlog) {
    Q_UNUSED(backlog); // Qt中不需要指定backlog，故忽略
    return server->isListening();
}

// 接受客户端的连接请求
QTcpSocket* acceptConnection(QTcpServer *server) {
    if (server->waitForNewConnection(300000)) {
        qDebug() << "新连接已到达";
        return server->nextPendingConnection();
    } else {
        qDebug() << "等待新连接超时或失败：" << server->errorString();
    }
    return nullptr;
}
// 客户端连接到服务器
bool connectToServer(QTcpSocket *socket, const QString &ip, quint16 port) {
    socket->connectToHost(ip, port);
    return socket->waitForConnected();
}

// 发送数据到对端
qint64 writeToSocket(QTcpSocket *socket, const QByteArray &data) {
    return socket->write(data);
}

// 从对端读取数据
QByteArray readFromSocket(QTcpSocket *socket) {
    if (socket->waitForReadyRead()) {
        return socket->readAll();
    }
    return QByteArray();
}

// 关闭Socket连接
void closeSocket(QTcpSocket *socket) {
    socket->close();
}
