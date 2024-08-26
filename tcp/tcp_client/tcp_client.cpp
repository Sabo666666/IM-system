#include <QCoreApplication>
#include "tcp_lib.h"

int main(int argc, char *argv[]) {
    QCoreApplication a(argc, argv);

    QTcpSocket *socket = createSocket();

    // 连接到服务器
    if (!connectToServer(socket, "127.0.0.1", 8080)) {
        qDebug() << "连接服务器失败";
        return -1;
    }

    // 向服务器发送数据
    writeToSocket(socket, "Hello from client!");

    // 读取服务器的响应
    QByteArray response = readFromSocket(socket);
    qDebug() << "收到的服务器响应：" << response;

    // 关闭连接
    closeSocket(socket);

    return a.exec();
}
