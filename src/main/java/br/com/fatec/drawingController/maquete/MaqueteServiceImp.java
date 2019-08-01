package br.com.fatec.drawingController.maquete;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaqueteServiceImp implements MaqueteService {

    @Autowired
    private MaqueteRepository maqueteRepository;

    @Override
    public boolean save(Maquete maquete) {
        maqueteRepository.save(maquete);
        // return usuarioRepository.existsById((int)usuario.getId());
        return maqueteRepository.existsById((maquete.getProjetoNumero()));

    }

    @Override
    public Maquete saveEnt(Maquete maquete) {
        return maqueteRepository.save(maquete);
    }

    @Override
    public Maquete remove(Maquete maquete) {

        maqueteRepository.delete(maquete);
        return maquete;
    }

    @Override
    public boolean update(Maquete maquete, Maquete maqueteUpdate) {

        Optional<Maquete> c = maqueteRepository.findById(maquete.getProjetoNumero());
        if (c.isPresent()) {
            maqueteUpdate.setProjetoNumero(c.get().getProjetoNumero());
            maqueteRepository.save(maqueteUpdate);

        }
        return maqueteRepository.existsById(maqueteUpdate.getProjetoNumero());
    }

    @Override
    public Optional<Maquete> findById(Long id) {

        return maqueteRepository.findById(id);

    }

    @Override
    public List<Maquete> findAll() {
        return maqueteRepository.findAll();
    }

}
