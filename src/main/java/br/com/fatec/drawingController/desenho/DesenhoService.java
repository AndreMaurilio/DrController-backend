package br.com.fatec.drawingController.desenho;

import java.util.List;
import java.text.ParseException;
import java.util.Date;
import br.com.fatec.drawingController.desenho.BodyCountStatus;

import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.generic.IGenericServiceCrud;
import br.com.fatec.drawingController.linha.Linha;
import br.com.fatec.drawingController.maquete.Maquete;
import br.com.fatec.drawingController.usuario.BodyDesGraficoDTO;

public interface DesenhoService extends IGenericServiceCrud<Desenho, Long> {

        /********** PLANT3D ************/
        public Desenho registrarDesenho(String idCad, String tag, String desContratado, String desSubtitulo,
                        String status, String revisao, Date dataIni, Date dataFim, String comentarios,
                        String nomeVerificador, String pipeService, String pipeSpec, String pID, int numFolhas,
                        Long idMaq);

        /*
         * public boolean cadastraDesenho(Desenho desenho);
         * 
         * 
         * public double tempoGastoPorDesenho();
         * 
         * public double desenhosAbertosPorDisciplina(String disciplina);
         * 
         * public List<Desenho> desenhosPorDesenhista(String nomeDesenhista);
         */

        public List<Desenho> mesmaTag(String tag);

        /*************** WEB *******************/

        // CONTAGEM POR STATUS E CALENDARIO
        public BodyCountStatus contagemPorStatusSelec(Date dIni, Date dFim);

        public BodyCountStatus contagemPorProjStatusSelec(Long maque, Date dIni, Date dFim);

        // CONTAGEM POR STATUS E MES ATUAL
        public BodyCountStatus contagemPorStatusDEFAULT(Date dIni);

        public BodyCountStatus contagemPorProjStatusDEFAULT(Long maque, Date dIni);

        // LISTA POR STATUS E CALENDARIO
        public List<Desenho> desenhosPorStatusCalend(String status, Date dIni, Date dFim);

        // LISTA TODOS DESENHOS POR PROJETO NA PAGINA "TABELAS"
        public List<Desenho> desenhosPorMaquete(Long numMaquete);

        // LISTA POR STATUS NO CAMPO "VER DETALHES" COM E SEM NUMERO DE PROJETO E
        // CALENDARIO
        public List<Desenho> desenhosPormaqueteVerDetCalend(Long maque, String Status, String dIni, String dFIm)
                        throws ParseException;

        // LISTA POR STATUS NO CAMPO "VER DETALHES" COM E SEM NUMERO DE PROJETO E MES
        // VINGENTE/DEFAULT
        public List<Desenho> desenhosPormaqueteVerDetDEFAULT(Long maque, String Status, String dIni)
                        throws ParseException;

        // RETORNA GRAFICO DEFAULT
        public List<BodyDesGraficoDTO> desGrafico();

        public List<BodyDesGraficoDTO> desGraficoSelect(Long proj, int Data);

        // BUSCA COM COLUNAS DINAMICAS

        public List<Desenho> buscaDeseParams(Long maquete, String nCamp, String busca);

}