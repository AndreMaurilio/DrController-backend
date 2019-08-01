package br.com.fatec.drawingController.desenho;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import br.com.fatec.drawingController.desenho.BodyCountStatus;

import br.com.fatec.drawingController.desenho.Desenho;
import br.com.fatec.drawingController.generic.IGenericServiceCrud;

public interface DesenhoService extends IGenericServiceCrud<Desenho, Long> {

        /********** PLANT3D ************/
        public Desenho registrarDesenho(String idCad, String tag, String desContratado, String desSubtitulo,
                        String status, String revisao, Date dataIni, Date dataFim, String comentarios,
                        String nomeVerificador, Long idMaq);

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

        public List<Desenho> desenhosPorMaquete(Long numMaquete);

}