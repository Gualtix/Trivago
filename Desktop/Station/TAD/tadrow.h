#ifndef TADROW_H
#define TADROW_H
#include <QString>

class TADRow
{
    int codigo;
    QString nombre;
    double latitud;
    double longitud;
public:
    TADRow();
    TADRow(int _row);
    ~TADRow();

    int getCodigo() const;
    void setCodigo(int value);
    QString getNombre() const;
    void setNombre(const QString &value);
    double getLatitud() const;
    void setLatitud(double value);
    double getLongitud() const;
    void setLongitud(double value);
    int compare(TADRow *value);
};

#endif // TADROW_H
