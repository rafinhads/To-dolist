package br.com.todo.repositorio;

import br.com.todo.model.TarefaVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TarefaRepositorio {

    private final JdbcTemplate jdbcTemplate;

    public TarefaRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<TarefaVO> rowMapper = (rs, rowNum) -> {
        TarefaVO tarefaVO = new TarefaVO();
        tarefaVO.setCodigo(rs.getLong("codigo"));
        tarefaVO.setDescricao(rs.getString("descricao"));
        tarefaVO.setCompleto(rs.getBoolean("completo"));
        return tarefaVO;
    };

    public List<TarefaVO> buscarLista() {
        String sql = "SELECT * FROM tarefas";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<TarefaVO> buscarPorCodigo(Long codigo) {
        String sql = "SELECT * FROM tarefas WHERE codigo = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{codigo}, rowMapper));
    }

    public void salvar(TarefaVO tarefaVO) {
        if (tarefaVO.getCodigo() == null) {
            String sql = "INSERT INTO tarefas (descricao, completo) VALUES (?, ?)";
            jdbcTemplate.update(sql, tarefaVO.getDescricao(), tarefaVO.isCompleto());
        } else {
            String sql = "UPDATE tarefas SET descricao = ?, completo = ? WHERE codigo = ?";
            jdbcTemplate.update(sql, tarefaVO.getDescricao(), tarefaVO.isCompleto(), tarefaVO.getCodigo());
        }
    }

    public void deletarPorCodigo(Long codigo) {
        String sql = "DELETE FROM tarefas WHERE codigo = ?";
        jdbcTemplate.update(sql, codigo);
    }

}