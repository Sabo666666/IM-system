#ifndef TCP_LIB_H
#define TCP_LIB_H

#include <QTcpSocket>
#include <QTcpServer>

// 创建一个Socket对象
QTcpSocket* createSocket();

// 绑定Socket到指定的IP和端口
bool bindSocket(QTcpServer *server, const QString &ip, quint16 port);

// 服务器端监听连接请求
bool listenForConnections(QTcpServer *server, int backlog);

// 接受客户端的连接请求
QTcpSocket* acceptConnection(QTcpServer *server);

// 客户端连接到服务器
bool connectToServer(QTcpSocket *socket, const QString &ip, quint16 port);

// 发送数据到对端
qint64 writeToSocket(QTcpSocket *socket, const QByteArray &data);

// 从对端读取数据
QByteArray readFromSocket(QTcpSocket *socket);

// 关闭Socket连接
void closeSocket(QTcpSocket *socket);

#endif // TCP_LIB_H
