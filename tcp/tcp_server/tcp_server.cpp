#include <QCoreApplication>
#include "tcp_lib.h"

int main(int argc, char *argv[]) {
    QCoreApplication a(argc, argv);

    QTcpServer server;
    if (!bindSocket(&server, "127.0.0.1", 8080)) {
        qDebug() << "绑定失败";
        return -1;
    }

    if (!listenForConnections(&server, 5)) {
        qDebug() << "监听失败";
        return -1;
    }

    qDebug() << "服务器等待连接...";

    QTcpSocket *clientSocket = acceptConnection(&server);
    if (clientSocket) {
        qDebug() << "客户端已连接";
        QByteArray data = readFromSocket(clientSocket);
        qDebug() << "接收到的数据：" << data;

        writeToSocket(clientSocket, "Hello from server!");

        closeSocket(clientSocket);
    } else {
        qDebug() << "连接接受失败";
    }

    return a.exec();
}
