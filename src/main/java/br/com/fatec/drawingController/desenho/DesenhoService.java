package br.com.fatec.drawingController.desenho;

import java.util.List;
import java.text.ParseException;
import java.util.Date;
import br.com.fatec.drawingController.desenho.BodyCountStatus;

import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.generic.IGenericServiceCrud;
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

        public BodyCountStatus contagemPorStatus();

        public BodyCountStatus contagemPorStatusSelec(Date dIni, Date dFim);

        public BodyCountStatus contagemPorStatusDEFAULT(Date dIni);

        public List<Desenho> desenhosPorMaquete(Long numMaquete);

        // Retorna relação Desenhistas e desenhos por Dia
        public List<BodyDesGraficoDTO> desGrafico();

}