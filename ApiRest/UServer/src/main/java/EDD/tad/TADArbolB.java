package EDD.tad;

import DAO.DataStructure;

public class TADArbolB extends DataStructure {
    private int codigoCurso;
    private String curso;

    public TADArbolB(int codigoCurso, String curso) {
        this.codigoCurso = codigoCurso;
        this.curso = curso;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return String.format("%d\\n%s",
                codigoCurso, curso);
    }

    @Override
    public String nodeName() {
        return "";
    }

    @Override
    public String createNode() {
        return "";
    }

    @Override
    public String getJSON() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        TADArbolB value = (TADArbolB) o;
        return Integer.compare(codigoCurso, value.getCodigoCurso());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
