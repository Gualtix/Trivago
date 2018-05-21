#ifndef DOUBLENODE_H
#define DOUBLENODE_H
#include <QString>

template <typename T>
class NodeList
{
    T data;
    NodeList<T> *next;
    NodeList<T> *preview;
public:
    NodeList();
    NodeList(T value);
    ~NodeList();
    T getData();
    void setData(T value);
    NodeList<T> *getNext();
    void setNext(NodeList<T> *value);
    NodeList<T> *getPreview();
    void setPreview(NodeList<T> *value);

    QString getNameNode();
    QString getTextNode();
};

#endif // DOUBLENODE_H

template <typename T>
NodeList<T>::NodeList()
{
    data = 0;
    next = 0;
    preview = 0;
}

template <typename T>
NodeList<T>::NodeList(T value)
{
    data = value;
    next = 0;
    preview = 0;
}

template <typename T>
NodeList<T>::~NodeList()
{
    if (data != 0)
        delete data;
    data = 0;
    next = 0;
    preview = 0;
}

template <typename T>
T NodeList<T>::getData()
{
    return data;
}

template <typename T>
void NodeList<T>::setData(T value)
{
    data = value;
}

template <typename T>
NodeList<T> *NodeList<T>::getNext()
{
    return next;
}

template <typename T>
void NodeList<T>::setNext(NodeList<T> *value)
{
    next = value;
}

template <typename T>
NodeList<T> *NodeList<T>::getPreview()
{
    return preview;
}

template <typename T>
void NodeList<T>::setPreview(NodeList<T> *value)
{
    preview = value;
}

template <typename T>
QString NodeList<T>::getNameNode()
{
    return data->getNodeName();
}

template <typename T>
QString NodeList<T>::getTextNode()
{
    return data->toString();
}
