package br.com.gerenciador_tarefas.controller;

import br.com.gerenciador_tarefas.dto.TarefaRequest;
import br.com.gerenciador_tarefas.dto.TarefaRequestUpdate;
import br.com.gerenciador_tarefas.dto.TarefaResponse;
import br.com.gerenciador_tarefas.entity.TarefaStatus;
import br.com.gerenciador_tarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

/**
 * Controller REST responsável pelos endpoints relacionados aos recursos Tarefa
 */
@Tag(
        name = "Tarefas",
        description = "Operações relacionadas ao gerenciamento de Tarefas"
)
@RestController
@RequestMapping("/v1/api/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    /**
     * Lista todas as tarefas cadastradas
     * @return Lista de Tarefas
     */

    @Operation(
            summary = "Lista de tarefas",
            description = "Retorna todas as tarefas cadastradas"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Tarefas retornadas com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    /**
     * Lista todas as tarefas pendentes cadastradas
     * @return Lista de Tarefas pendentes
     */

    @Operation(
            summary = "Lista de tarefas pendentes",
            description = "Retorna todas as tarefas pendentes cadastradas"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Tarefas pendentes retornadas com sucesso"
    )
    @GetMapping("/filtrados-pendentes")
    public ResponseEntity<List<TarefaResponse>> listarPendentes(@RequestParam ("status")
                                                                TarefaStatus status){
        return ResponseEntity.ok(service.listarPendentes());
    }

    /**
     * Lista todas as tarefas concluidas cadastradas
     * @return Lista de Tarefas concluidas
     */

    @Operation(
            summary = "Lista de tarefas concluidas",
            description = "Retorna todas as tarefas concluidas cadastradas"
    )
    @ApiResponse (
            responseCode = "200",
            description = "Tarefas concluidas retornadas com sucesso"
    )
    @GetMapping("/filtrados-concluidos")
    public ResponseEntity<List<TarefaResponse>> listarConcluidos(@RequestParam ("status")
                                                                TarefaStatus status){
        return ResponseEntity.ok(service.listarConcluidos());
    }

    /**
     * Busca uma Tarefa pelo seu Identificador
     * @param id Identificador da tarefa
     * @return DTO {@link TarefaResponse} com os dados da tarefa encontrado
     */
    @Operation(
            summary = "Busca tarefa por ID",
            description = "Retorna os detalhes de uma tarefa específica com no seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarefa encontrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarPorId(
            @Parameter(description = "identificador único da tarefa", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }



    /**
     * Cadastra uma nova tarefa
     * @param request DTO com os dados necessários para criação da tarefa
     * @return DTO {@link TarefaResponse} com a tarefa cadastrada e cabeçalho location
     */

    @Operation(
            summary = "Cadastro de uma tarefa",
            description = "Cria uma nova tarefa"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarefa criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<TarefaResponse> criarTarefa(@Valid @RequestBody TarefaRequest request) {
        TarefaResponse tarefa = service.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefa.id())
                .toUri();
        return ResponseEntity.created(uri).body(tarefa);
    }

    /**
     * Atualiza os dados de uma tarefa existente
     * @param id Identificador da tarefa a ser atualizada
     * @param request DTO com os novos dados da tarefa
     * @return DTO {@link TarefaResponse} atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(
            @Parameter(description = "Identificador único do produto", example = "1")
            @PathVariable Long id, @Valid @RequestBody TarefaRequestUpdate request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    /**
     * Atualiza apenas o status de uma tarefa existente
     * @param id Identificador da tarefa a ser atualizada
     * @return DTO {@link TarefaResponse} atualizado parcialmente
     */
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponse> atualizarTarefaParcialmente(@PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarParcialmente(id));
    }

    /**
     * Remove uma tarefa do gerenciador de tarefas
     * @param id Identificador da tarefa a ser removida
     * @return Resposta sem conteúdo (HTTP 204 No Content)
     */
    @Operation(
            summary = "Remove uma tarefa",
            description = "Realiza a exclusão de uma tarefa"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Tarefa removida com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(
            @Parameter(description = "Identificador único do produto", example = "1")
            @PathVariable Long id) {
        service.remover(id);

        return ResponseEntity.noContent().build();
    }
}
