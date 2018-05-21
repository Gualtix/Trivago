#include "tadrow.h"

int TADRow::getCodigo() const
{
    return codigo;
}

void TADRow::setCodigo(int value)
{
    codigo = value;
}

QString TADRow::getNombre() const
{
    return nombre;
}

void TADRow::setNombre(const QString &value)
{
    nombre = value;
}

double TADRow::getLatitud() const
{
    return latitud;
}

void TADRow::setLatitud(double value)
{
    latitud = value;
}

double TADRow::getLongitud() const
{
    return longitud;
}

void TADRow::setLongitud(double value)
{
    longitud = value;
}

int TADRow::compare(TADRow *value)
{
    if (codigo > value->getCodigo())
        return 1;
    if (codigo < value->getCodigo())
        return -1;

    return 0;
}
