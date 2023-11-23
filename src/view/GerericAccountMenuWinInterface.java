package view;

import javafx.scene.layout.VBox;

/**
 * Interface para construir interfaces gráficas de menu de conta genéricas.
 */
public interface GerericAccountMenuWinInterface
{
    /**
     * Adiciona elementos ao contêiner especificado
     *
     * @param mainPane O contêiner principal onde os elementos serão adicionados.
     */
    public void addElements(VBox mainPane);
}
