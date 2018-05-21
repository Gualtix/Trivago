#ifndef DOUBLELIST_H
#define DOUBLELIST_H
#include <QFile>
#include <QTextStream>
#include "doublenode.h"

template <typename T>
class List
{
    int count;
    NodeList<T> *head;
    NodeList<T> *tail;

    void insert(NodeList<T> *current, T value);
    NodeList<T> *get(NodeList<T> *current, T value);
public:
    List();
    List(List<T> *value);
    ~List();

    /* CAPACIDAD */
    bool isEmpty();
    int size();
    void clear();

    /* MODIFICADORES */
    void push_front(T value);
    void push_back(T value);
    void pop_front();
    void pop_back();
    void insert(T value);
    void erase(T value);
    NodeList<T> *removeFirst();
    NodeList<T> *removeLast();
    NodeList<T> *removeOne(T value);

    /* OPERATIONS */
    NodeList<T> *first();
    NodeList<T> *last();
    T front();
    T back();
    NodeList<T> *get(T value);
    bool startsWith(T value);
    bool endsWith(T value);
    void sort();
    void reverse();

    /* REPORTES */
    void graph(QString filename);

    /* SOBRECARGA DE OPERADORES */
    List<T> operator<<(T value);
};

#endif // DOUBLELIST_H

template <typename T>
void List<T>::insert(NodeList<T> *current, T value)
{
    int compare = current->getData()->compare(value);

    if (compare == 0)
        return;
    else
    {
        if (compare > 0)
        {
            NodeList<T> *temporal = new NodeList<T>(value);
            temporal->setNext(current);
            temporal->setPreview(current->getPreview());
            current->getPreview()->setNext(temporal);
            current->setPreview(temporal);
        }
        else
            insert(current->getNext(), value);
    }
}

template <typename T>
NodeList<T> *List<T>::get(NodeList<T> *current, T value)
{
    if (current == NULL)
        return NULL;

    int compare = current->getData()->compare(value);

    if (compare == 0)
        return current;
    else
    {
        if (compare < 0)
            return get(current->getNext(), value);
        else
            return NULL;
    }
}

template <typename T>
List<T>::List()
{
    count = 0;
    head = 0;
    tail = 0;
}

template <typename T>
List<T>::List(List<T> *value)
{
    head = value->front();
    tail = value->back();
    count = value->size();

    value = NULL;
}

template <typename T>
List<T>::~List()
{
    clear();
}

template <typename T>
NodeList<T> *List<T>::first()
{
    return head;
}

template <typename T>
NodeList<T> *List<T>::last()
{
    return tail;
}

template <typename T>
T List<T>::front()
{
    return isEmpty() ? NULL : head->getData();
}

template <typename T>
T List<T>::back()
{
    return isEmpty() ? NULL: tail->getData();
}

template <typename T>
NodeList<T> *List<T>::get(T value)
{
    if (isEmpty())
        return NULL;

    if (startsWith(value))
        return first();
    if (endsWith(value))
        return last();

    if (head->getData()->compare(value) < 0
            && tail->getData()->compare(value) > 0)
        return get(head->next, value);
    else
        return NULL;
}

template <typename T>
bool List<T>::startsWith(T value)
{
    if (isEmpty())
        return false;

    return (value->compare(head->getData()) == 0) ? true: false;
}

template <typename T>
bool List<T>::endsWith(T value)
{
    if (isEmpty())
        return false;

    return (value->compare(tail->getData()) == 0) ? true : false;
}

template <typename T>
bool List<T>::isEmpty()
{
    return head == NULL;
}

template <typename T>
int List<T>::size()
{
    return count;
}

template <typename T>
void List<T>::clear()
{
    while (!isEmpty())
        pop_front();
}

template <typename T>
void List<T>::push_front(T value)
{
    NodeList<T> *node = new NodeList<T>(value);

    if (isEmpty())
        head = tail = node;
    else
    {
        node->setNext(head);
        head->setPreview(node);
        head = node;
    }
    count++;
}

template <typename T>
void List<T>::push_back(T value)
{
    NodeList<T> *node = new NodeList<T>(value);

    if (isEmpty())
        head = tail = node;
    else
    {
        tail->setNext(node);
        node->setPreview(tail);
        tail = node;
    }
    count++;
}

template <typename T>
void List<T>::pop_front()
{
    if (isEmpty())
        return;

    NodeList<T> *temporal = head;

    head = temporal->getNext();
    if (size() > 1)
        head->setPreview(NULL);
    else
        tail = head;

    delete temporal;
    temporal = NULL;
    count--;
}

template <typename T>
void List<T>::pop_back()
{
    if (isEmpty())
        return;

    NodeList<T> *temporal = tail;

    tail = temporal->getPreview();
    if (size() > 1)
        tail->setNext(NULL);
    else
        head = tail;

    delete temporal;
    temporal = NULL;
    count--;
}

template <typename T>
void List<T>::insert(T value)
{
    if (isEmpty())
    {
        NodeList<T> *temporal = new NodeList<T>(value);
        head = tail = temporal;
    }
    else
    {
        if (head->getData()->compare(value) > 0)
            push_front(value);
        else if (tail->getData()->compare(value) < 0)
            push_back(value);
        else
            insert(head->getNext(), value);
    }
}

template <typename T>
void List<T>::erase(T value)
{
    NodeList<T> *temporal = removeOne(value);
    delete temporal;
    temporal = NULL;
}

template <typename T>
NodeList<T> *List<T>::removeFirst()
{
    NodeList<T> *temporal = head;
    head = temporal->getNext();
    if (size() > 1)
        head->setPreview(NULL);
    else
        tail = head;

    return temporal;
}

template <typename T>
NodeList<T> *List<T>::removeLast()
{
    NodeList<T> *temporal = tail;
    tail = temporal->getPreview();
    if (size() > 1)
        tail->setNext(NULL);
    else
        head = tail;

    return temporal;
}

template <typename T>
NodeList<T> *List<T>::removeOne(T value)
{
    if (startsWith(value))
        return removeFirst();
    if (endsWith(value))
        return removeLast();

    if (head->getData()->compare(value) < 0
            && tail->getData()->compare(value) > 0)
    {
        NodeList<T> *temporal = get(head->getNext(), value);

        if (temporal->getNext() != NULL)
            temporal->getNext()->setPreview(temporal->getPreview());
        if (temporal->getPreview() != NULL)
            temporal->getPreview()->setNext(temporal->getNext());

        temporal->setNext(NULL);
        temporal->setPreview(NULL);

        return temporal;
    }
    else
        return NULL;
}

template <typename T>
void List<T>::sort()
{
}

template <typename T>
void List<T>::reverse()
{
}

template <typename T>
void List<T>::graph(QString filename)
{
    QFile file(filename + ".dot");

    if (file.open(QFile::WriteOnly | QFile::Text))
    {
        NodeList<T> *temporal = head;
        QTextStream out(&file);

        out << "digraph " + filename + " {\n";
        out << "\trankdir = LR;\n";
        out << "\tnode [shape = record]\n";
        flush(out);

        while (temporal != NULL)
            {
                out << "\t" << temporal->getNameNode();
                out << " [label = \"" << temporal->getTextNode() << "\"];\n";
                flush(out);

                if (temporal->getPreview() != NULL)
                {
                    out << "\t" << temporal->getNameNode();
                    out << " -> " << temporal->getPreview()->getNameNode() << ";\n";
                    flush(out);
                }

                if (temporal->getNext() != NULL)
                {
                    out << "\t" << temporal->getNameNode();
                    out << " -> " << temporal->getNext()->getNameNode() << ";\n";
                    flush(out);
                }

                temporal = temporal->getNext();
        }

        out << "}";
        flush(out);

        file.close();
        QString cmd("dot -Tpng ");
        cmd.append(filename + ".dot ");
        cmd.append("-o " + filename + ".png");
        system(cmd.toLatin1().data());
    }
}

template <typename T>
List<T> List<T>::operator<<(T value)
{
    push_back(value);

    return this;
}
