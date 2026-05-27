-- Script de inserção MASSIVA de dados de teste
-- PostgreSQL 18 - Base de processos administrativos
-- Geração de pelo menos 100 registros por entidade forte

\c processos

BEGIN;

-- ==================================================================
-- GERAÇÃO DE PESSOAS FÍSICAS (200 registros)
-- ==================================================================

INSERT INTO TB_PESSOA_FISICA (
    CO_SEQ_PESSOA_FISICA, NO_PESSOA_FISICA, NU_CPF, DT_NASCIMENTO,
    DS_EMAIL, NU_TELEFONE, DS_LOGRADOURO, DS_COMPLEMENTO,
    NO_BAIRRO, NO_MUNICIPIO, SG_UF, NU_CEP, ST_REGISTRO_ATIVO
)
SELECT
    i,
    CASE
        WHEN i % 2 = 1 THEN
            (ARRAY['Carlos', 'Ricardo', 'Pedro', 'Rafael', 'Thiago', 'Bruno', 'Eduardo', 'Marcelo', 'Anderson', 'Rodrigo', 'Leandro', 'Fabio', 'Gustavo', 'Felipe', 'Henrique', 'Diego', 'Lucas', 'Vitor', 'Alexandre', 'Paulo'])[((i-1) % 20) + 1]
        ELSE
            (ARRAY['Ana', 'Fernanda', 'Mariana', 'Juliana', 'Camila', 'Luciana', 'Patricia', 'Renata', 'Cristina', 'Vanessa', 'Sabrina', 'Beatriz', 'Leticia', 'Aline', 'Claudia', 'Monica', 'Carla', 'Tatiana', 'Simone', 'Natalia'])[((i-1) % 20) + 1]
    END || ' ' ||
    (ARRAY['Silva', 'Santos', 'Oliveira', 'Souza', 'Rodrigues', 'Ferreira', 'Almeida', 'Lima', 'Pereira', 'Carvalho', 'Costa', 'Gomes', 'Martins', 'Araujo', 'Melo', 'Barbosa', 'Ribeiro', 'Rocha', 'Cardoso', 'Correia'])[((i-1) % 20) + 1] || ' ' ||
    (ARRAY['Nascimento', 'Castro', 'Moreira', 'Teixeira', 'Monteiro', 'Freitas', 'Pinto', 'Mendes', 'Azevedo', 'Borges', 'Vieira', 'Campos', 'Dias', 'Ramos', 'Lopes', 'Soares', 'Machado', 'Cunha', 'Coelho', 'Cavalcanti'])[(i % 20) + 1],
    LPAD((10000000000::bigint + i)::text, 11, '0'),
    DATE '1970-01-01' + (i * 100),
    lower(
        CASE
            WHEN i % 2 = 1 THEN
                (ARRAY['carlos', 'ricardo', 'pedro', 'rafael', 'thiago', 'bruno', 'eduardo', 'marcelo', 'anderson', 'rodrigo', 'leandro', 'fabio', 'gustavo', 'felipe', 'henrique', 'diego', 'lucas', 'vitor', 'alexandre', 'paulo'])[((i-1) % 20) + 1]
            ELSE
                (ARRAY['ana', 'fernanda', 'mariana', 'juliana', 'camila', 'luciana', 'patricia', 'renata', 'cristina', 'vanessa', 'sabrina', 'beatriz', 'leticia', 'aline', 'claudia', 'monica', 'carla', 'tatiana', 'simone', 'natalia'])[((i-1) % 20) + 1]
        END || '.' ||
        lower((ARRAY['silva', 'santos', 'oliveira', 'souza', 'rodrigues', 'ferreira', 'almeida', 'lima', 'pereira', 'carvalho', 'costa', 'gomes', 'martins', 'araujo', 'melo', 'barbosa', 'ribeiro', 'rocha', 'cardoso', 'correia'])[((i-1) % 20) + 1]) || '@email.com'
    ),
    '11' || LPAD((900000000 + i)::text, 9, '0'),
    (ARRAY['Rua das Flores', 'Avenida Brasil', 'Rua Augusta', 'Avenida Paulista', 'Rua do Comércio', 'Rua XV de Novembro', 'Avenida Getúlio Vargas', 'Rua Direita', 'Avenida Central', 'Rua José Bonifácio'])[((i-1) % 10) + 1] || ', ' || (100 + i),
    CASE WHEN i % 3 = 0 THEN 'Apto ' || (i % 100) ELSE NULL END,
    CASE i % 5
        WHEN 0 THEN 'Centro'
        WHEN 1 THEN 'Jardim Paulista'
        WHEN 2 THEN 'Vila Nova'
        WHEN 3 THEN 'Bela Vista'
        ELSE 'Vila Mariana'
    END,
    (ARRAY['São Paulo', 'Rio de Janeiro', 'Belo Horizonte', 'Curitiba', 'Porto Alegre', 'Fortaleza', 'Salvador', 'Recife', 'Manaus', 'Goiânia'])[((i-1) % 10) + 1],
    (ARRAY['SP', 'RJ', 'MG', 'PR', 'RS', 'CE', 'BA', 'PE', 'AM', 'GO'])[((i-1) % 10) + 1],
    LPAD((10000 + (i % 90000))::text, 8, '0'),
    'S'
FROM generate_series(1, 200) AS i;

-- ==================================================================
-- GERAÇÃO DE PESSOAS JURÍDICAS (100 registros)
-- ==================================================================

INSERT INTO TB_PESSOA_JURIDICA (
    CO_SEQ_PESSOA_JURIDICA, NO_RAZAO_SOCIAL, NO_NOME_FANTASIA, NU_CNPJ,
    DS_EMAIL, NU_TELEFONE, DS_LOGRADOURO, DS_COMPLEMENTO,
    NO_BAIRRO, NO_MUNICIPIO, SG_UF, NU_CEP, ST_REGISTRO_ATIVO
)
SELECT
    i,
    CASE i % 10
        WHEN 0 THEN 'Construtora ' || (ARRAY['Silva', 'Santos', 'Oliveira', 'Souza', 'Rodrigues'])[((i-1) % 5) + 1] || ' Ltda'
        WHEN 1 THEN 'Comercial ' || (ARRAY['Ferreira', 'Almeida', 'Lima', 'Pereira', 'Carvalho'])[((i-1) % 5) + 1] || ' S.A.'
        WHEN 2 THEN 'Indústria ' || (ARRAY['Costa', 'Gomes', 'Martins', 'Araujo', 'Melo'])[((i-1) % 5) + 1] || ' Ltda'
        WHEN 3 THEN 'Serviços ' || (ARRAY['Barbosa', 'Ribeiro', 'Rocha', 'Cardoso', 'Correia'])[((i-1) % 5) + 1] || ' Eireli'
        WHEN 4 THEN 'Tecnologia ' || (ARRAY['Nascimento', 'Castro', 'Moreira', 'Teixeira', 'Monteiro'])[((i-1) % 5) + 1] || ' S.A.'
        WHEN 5 THEN 'Transportes ' || (ARRAY['Freitas', 'Pinto', 'Mendes', 'Azevedo', 'Borges'])[((i-1) % 5) + 1] || ' Ltda'
        WHEN 6 THEN 'Consultoria ' || (ARRAY['Vieira', 'Campos', 'Dias', 'Ramos', 'Lopes'])[((i-1) % 5) + 1] || ' ME'
        WHEN 7 THEN 'Alimentícia ' || (ARRAY['Soares', 'Machado', 'Cunha', 'Coelho', 'Cavalcanti'])[((i-1) % 5) + 1] || ' Ltda'
        WHEN 8 THEN 'Farmacêutica ' || (ARRAY['Vasconcelos', 'Rezende', 'Andrade', 'Fonseca', 'Morais'])[((i-1) % 5) + 1] || ' S.A.'
        ELSE 'Educacional ' || (ARRAY['Amaral', 'Nogueira', 'Magalhães', 'Tavares', 'Siqueira'])[((i-1) % 5) + 1] || ' Ltda'
    END,
    CASE i % 5
        WHEN 0 THEN (ARRAY['Silva', 'Santos', 'Oliveira', 'Souza', 'Rodrigues'])[((i-1) % 5) + 1] || ' & Cia'
        WHEN 1 THEN 'Grupo ' || (ARRAY['Ferreira', 'Almeida', 'Lima', 'Pereira', 'Carvalho'])[((i-1) % 5) + 1]
        WHEN 2 THEN (ARRAY['Costa', 'Gomes', 'Martins', 'Araujo', 'Melo'])[((i-1) % 5) + 1] || ' Corp'
        WHEN 3 THEN 'Empresa ' || (ARRAY['Barbosa', 'Ribeiro', 'Rocha', 'Cardoso', 'Correia'])[((i-1) % 5) + 1]
        ELSE (ARRAY['Nascimento', 'Castro', 'Moreira', 'Teixeira', 'Monteiro'])[((i-1) % 5) + 1] || ' Solutions'
    END,
    LPAD((10000000000000::bigint + i)::text, 14, '0'),
    'contato@empresa' || i || '.com.br',
    '11' || LPAD((800000000 + i)::text, 9, '0'),
    (ARRAY['Avenida Industrial', 'Rua dos Empresários', 'Avenida Comercial', 'Rua das Corporações', 'Avenida Empresarial'])[((i-1) % 5) + 1] || ', ' || (500 + i * 10),
    CASE WHEN i % 4 = 0 THEN 'Sala ' || (i % 50 + 100) ELSE 'Galpão ' || (i % 20 + 1) END,
    CASE i % 4
        WHEN 0 THEN 'Distrito Industrial'
        WHEN 1 THEN 'Centro Empresarial'
        WHEN 2 THEN 'Zona Comercial'
        ELSE 'Área Industrial'
    END,
    (ARRAY['São Paulo', 'Rio de Janeiro', 'Belo Horizonte', 'Curitiba', 'Porto Alegre'])[((i-1) % 5) + 1],
    (ARRAY['SP', 'RJ', 'MG', 'PR', 'RS'])[((i-1) % 5) + 1],
    LPAD((20000 + (i % 80000))::text, 8, '0'),
    'S'
FROM generate_series(1, 100) AS i;

-- ==================================================================
-- SETORES (25 setores realistas)
-- ==================================================================

INSERT INTO TB_SETOR (CO_SEQ_SETOR, NO_SETOR, SG_SETOR, DS_SETOR, ST_REGISTRO_ATIVO) VALUES
(1, 'Protocolo e Atendimento', 'PROT', 'Setor responsável pelo protocolo de documentos e atendimento ao público', 'S'),
(2, 'Setor Jurídico', 'JUR', 'Setor responsável pela análise jurídica e pareceres técnicos', 'S'),
(3, 'Administração Geral', 'ADM', 'Setor responsável pela administração geral e decisões administrativas', 'S'),
(4, 'Recursos Humanos', 'RH', 'Setor responsável pela gestão de pessoal e recursos humanos', 'S'),
(5, 'Financeiro e Orçamento', 'FIN', 'Setor responsável pelo controle financeiro e orçamentário', 'S'),
(6, 'Compras e Licitações', 'COMP', 'Setor responsável por processos de compras e licitações públicas', 'S'),
(7, 'Tecnologia da Informação', 'TI', 'Setor responsável pela infraestrutura e sistemas de informação', 'S'),
(8, 'Auditoria Interna', 'AUD', 'Setor responsável pela auditoria e controle interno', 'S'),
(9, 'Obras e Infraestrutura', 'OBRAS', 'Setor responsável por obras públicas e infraestrutura urbana', 'S'),
(10, 'Meio Ambiente e Sustentabilidade', 'AMB', 'Setor responsável por questões ambientais e sustentabilidade', 'S'),
(11, 'Saúde Pública', 'SAUDE', 'Setor responsável pela gestão da saúde pública municipal', 'S'),
(12, 'Educação e Cultura', 'EDUC', 'Setor responsável pela educação municipal e atividades culturais', 'S'),
(13, 'Cultura e Patrimônio', 'CULT', 'Setor responsável pela preservação cultural e patrimonial', 'S'),
(14, 'Esporte e Lazer', 'ESP', 'Setor responsável por atividades esportivas e de lazer', 'S'),
(15, 'Assistência Social', 'ASSOC', 'Setor responsável por programas de assistência social', 'S'),
(16, 'Planejamento Urbano', 'PLAN', 'Setor responsável pelo planejamento e desenvolvimento urbano', 'S'),
(17, 'Tributação e Arrecadação', 'TRIB', 'Setor responsável pela tributação municipal e arrecadação', 'S'),
(18, 'Arrecadação e Cobrança', 'ARREC', 'Setor responsável pela arrecadação e cobrança de tributos', 'S'),
(19, 'Fiscalização Municipal', 'FISC', 'Setor responsável pela fiscalização de atividades municipais', 'S'),
(20, 'Licenciamento e Alvarás', 'LIC', 'Setor responsável por licenças e alvarás de funcionamento', 'S'),
(21, 'Urbanismo e Zoneamento', 'URB', 'Setor responsável por questões de urbanismo e zoneamento', 'S'),
(22, 'Transporte e Mobilidade', 'TRANS', 'Setor responsável pelo transporte público e mobilidade urbana', 'S'),
(23, 'Segurança Institucional', 'SEG', 'Setor responsável pela segurança das instalações públicas', 'S'),
(24, 'Arquivo e Documentação', 'ARQ', 'Setor responsável pela gestão documental e arquivo público', 'S'),
(25, 'Ouvidoria Municipal', 'OUV', 'Setor responsável pelo atendimento de demandas da população', 'S');

-- ==================================================================
-- USUÁRIOS (120 usuários dos primeiros 120 PF)
-- ==================================================================

INSERT INTO TB_USUARIO (
    CO_SEQ_USUARIO, DS_SUB_OIDC, CO_SEQ_PESSOA_FISICA,
    DT_PRIMEIRO_ACESSO, DT_ULTIMO_ACESSO, ST_REGISTRO_ATIVO
)
SELECT
    i,
    'auth0|user' || LPAD(i::text, 6, '0') || 'sub' || LPAD((random() * 999999)::integer::text, 6, '0'),
    i,
    TIMESTAMP '2024-01-01 08:00:00' + (i || ' days')::INTERVAL,
    TIMESTAMP '2026-05-01 08:00:00' + (i % 14 || ' days')::INTERVAL,
    'S'
FROM generate_series(1, 120) AS i;

-- ==================================================================
-- ANALISTAS (100 analistas das PF de 101 a 200, evitando sobreposição com usuários)
-- ==================================================================

INSERT INTO TB_ANALISTA (
    CO_SEQ_ANALISTA, CO_SEQ_PESSOA_FISICA, CO_SEQ_SETOR,
    DT_VINCULO, ST_REGISTRO_ATIVO
)
SELECT
    i,
    100 + i,  -- PF de 101 a 200 (algumas PF podem ser tanto usuário quanto analista)
    ((i - 1) % 25) + 1,  -- Distribui uniformemente pelos 25 setores
    DATE '2022-01-01' + ((i * 30) % 1000),  -- Datas de vínculo variadas
    'S'
FROM generate_series(1, 100) AS i;

-- ==================================================================
-- PROCESSOS (200 processos)
-- ==================================================================

INSERT INTO TB_PROCESSO (
    CO_SEQ_PROCESSO, NU_PROCESSO, DS_ASSUNTO, DT_ABERTURA,
    CO_SEQ_USUARIO_ABERTURA, CO_SITUACAO_PROCESSO, ST_REGISTRO_ATIVO
)
SELECT
    i,
    '2024.' || LPAD(((i-1) / 1000 + 1)::text, 4, '0') || '.' || LPAD(i::text, 6, '0'),
    CASE i % 20
        WHEN 0 THEN 'Licença para construção de edifício residencial'
        WHEN 1 THEN 'Registro de marca e patente'
        WHEN 2 THEN 'Solicitação de alvará de funcionamento'
        WHEN 3 THEN 'Pedido de revisão cadastral'
        WHEN 4 THEN 'Licenciamento ambiental para indústria'
        WHEN 5 THEN 'Autorização para evento público'
        WHEN 6 THEN 'Solicitação de certidão negativa'
        WHEN 7 THEN 'Pedido de isenção de taxa municipal'
        WHEN 8 THEN 'Licença para instalação comercial'
        WHEN 9 THEN 'Registro de software e sistema'
        WHEN 10 THEN 'Autorização para obra pública'
        WHEN 11 THEN 'Solicitação de numeração predial'
        WHEN 12 THEN 'Pedido de habite-se'
        WHEN 13 THEN 'Licença para transporte escolar'
        WHEN 14 THEN 'Autorização sanitária'
        WHEN 15 THEN 'Pedido de parcelamento de débito'
        WHEN 16 THEN 'Solicitação de certidão de tempo de serviço'
        WHEN 17 THEN 'Licença para atividade rural'
        WHEN 18 THEN 'Autorização para corte de árvores'
        ELSE 'Pedido de segunda via de documentos'
    END,
    TIMESTAMP '2024-01-01 08:00:00' + (i * 3 || ' days')::INTERVAL,
    ((i - 1) % 120) + 1,  -- Usuários de 1 a 120
    CASE
        WHEN i % 10 < 4 THEN 'ABERTO'         -- 40%
        WHEN i % 10 < 8 THEN 'EM_TRAMITACAO' -- 40%
        ELSE 'ENCERRADO'                      -- 20%
    END,
    'S'
FROM generate_series(1, 200) AS i;

-- ==================================================================
-- PARTES DOS PROCESSOS (400+ registros, 2-3 por processo)
-- ==================================================================

-- Primeira parte de cada processo (sempre PF requerente)
INSERT INTO RL_PROCESSO_PARTE (
    CO_SEQ_PARTE, CO_SEQ_PROCESSO, TP_PESSOA, CO_SEQ_PESSOA_FISICA,
    CO_SEQ_PESSOA_JURIDICA, CO_TIPO_PARTE, DT_VINCULO, ST_REGISTRO_ATIVO
)
SELECT
    i,
    i,  -- Processo correspondente
    'F',  -- Pessoa física
    ((i - 1) % 200) + 1,  -- PF de 1 a 200
    NULL,
    'REQUERENTE',
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i),
    'S'
FROM generate_series(1, 200) AS i;

-- Segunda parte de cada processo (PJ como interessado/requerido)
INSERT INTO RL_PROCESSO_PARTE (
    CO_SEQ_PARTE, CO_SEQ_PROCESSO, TP_PESSOA, CO_SEQ_PESSOA_FISICA,
    CO_SEQ_PESSOA_JURIDICA, CO_TIPO_PARTE, DT_VINCULO, ST_REGISTRO_ATIVO
)
SELECT
    200 + i,
    i,  -- Processo correspondente
    'J',  -- Pessoa jurídica
    NULL,
    ((i - 1) % 100) + 1,  -- PJ de 1 a 100
    CASE i % 3
        WHEN 0 THEN 'INTERESSADO'
        WHEN 1 THEN 'REQUERIDO'
        ELSE 'REPRESENTANTE'
    END,
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i) + INTERVAL '1 hour',
    'S'
FROM generate_series(1, 200) AS i;

-- Terceira parte para processos pares (mais complexos)
INSERT INTO RL_PROCESSO_PARTE (
    CO_SEQ_PARTE, CO_SEQ_PROCESSO, TP_PESSOA, CO_SEQ_PESSOA_FISICA,
    CO_SEQ_PESSOA_JURIDICA, CO_TIPO_PARTE, DT_VINCULO, ST_REGISTRO_ATIVO
)
SELECT
    400 + i,
    i * 2,  -- Apenas processos pares
    'F',  -- Pessoa física
    ((i - 1) % 200) + 1,
    NULL,
    'REPRESENTANTE',
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i * 2) + INTERVAL '2 hours',
    'S'
FROM generate_series(1, 100) AS i;

-- ==================================================================
-- REGISTROS DOS PROCESSOS (600+ registros, 3+ por processo)
-- ==================================================================

-- Primeiro registro de cada processo (protocolo)
INSERT INTO TB_REGISTRO (
    CO_SEQ_REGISTRO, CO_SEQ_PROCESSO, DS_REGISTRO, DT_REGISTRO,
    CO_SEQ_USUARIO, ST_REGISTRO_ATIVO
)
SELECT
    i,
    i,
    'Processo protocolado com documentação ' ||
    CASE i % 4
        WHEN 0 THEN 'completa. Verificados todos os documentos obrigatórios.'
        WHEN 1 THEN 'parcial. Solicitada complementação documental.'
        WHEN 2 THEN 'em conformidade. Documentos validados tecnicamente.'
        ELSE 'aprovada. Iniciada análise técnica detalhada.'
    END,
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i) + INTERVAL '15 minutes',
    ((i - 1) % 120) + 1,
    'S'
FROM generate_series(1, 200) AS i;

-- Segundo registro de cada processo (análise)
INSERT INTO TB_REGISTRO (
    CO_SEQ_REGISTRO, CO_SEQ_PROCESSO, DS_REGISTRO, DT_REGISTRO,
    CO_SEQ_USUARIO, ST_REGISTRO_ATIVO
)
SELECT
    200 + i,
    i,
    'Análise técnica ' ||
    CASE i % 5
        WHEN 0 THEN 'aprovada. Processo atende aos requisitos legais e técnicos.'
        WHEN 1 THEN 'em andamento. Verificação de conformidade com normas vigentes.'
        WHEN 2 THEN 'concluída com ressalvas. Necessária adequação de documentos.'
        WHEN 3 THEN 'finalizada com aprovação. Encaminhado para próxima etapa.'
        ELSE 'pendente. Aguardando informações complementares do requerente.'
    END,
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i) + INTERVAL '3 days',
    ((i - 1) % 120) + 1,
    'S'
FROM generate_series(1, 200) AS i;

-- Terceiro registro de cada processo (andamento/decisão)
INSERT INTO TB_REGISTRO (
    CO_SEQ_REGISTRO, CO_SEQ_PROCESSO, DS_REGISTRO, DT_REGISTRO,
    CO_SEQ_USUARIO, ST_REGISTRO_ATIVO
)
SELECT
    400 + i,
    i,
    CASE
        WHEN (SELECT CO_SITUACAO_PROCESSO FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i) = 'ENCERRADO'
        THEN 'Processo finalizado. ' ||
             CASE i % 3
                 WHEN 0 THEN 'Licença deferida e enviada ao requerente.'
                 WHEN 1 THEN 'Autorização emitida conforme solicitado.'
                 ELSE 'Certificado expedido e disponibilizado.'
             END
        WHEN (SELECT CO_SITUACAO_PROCESSO FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i) = 'EM_TRAMITACAO'
        THEN 'Processo em tramitação. ' ||
             CASE i % 4
                 WHEN 0 THEN 'Aguardando análise do setor competente.'
                 WHEN 1 THEN 'Em fase de avaliação técnica especializada.'
                 WHEN 2 THEN 'Submetido à aprovação da autoridade superior.'
                 ELSE 'Pendente de vistoria técnica no local.'
             END
        ELSE 'Processo em análise. ' ||
             CASE i % 3
                 WHEN 0 THEN 'Documentação em avaliação preliminar.'
                 WHEN 1 THEN 'Aguardando distribuição para analista responsável.'
                 ELSE 'Em fase inicial de processamento.'
             END
    END,
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i) + INTERVAL '7 days',
    ((i - 1) % 120) + 1,
    'S'
FROM generate_series(1, 200) AS i;

-- Registros adicionais para processos em tramitação
INSERT INTO TB_REGISTRO (
    CO_SEQ_REGISTRO, CO_SEQ_PROCESSO, DS_REGISTRO, DT_REGISTRO,
    CO_SEQ_USUARIO, ST_REGISTRO_ATIVO
)
SELECT
    600 + ROW_NUMBER() OVER (ORDER BY p.CO_SEQ_PROCESSO),
    p.CO_SEQ_PROCESSO,
    'Atualização de andamento. ' ||
    CASE (ROW_NUMBER() OVER (ORDER BY p.CO_SEQ_PROCESSO)) % 6
        WHEN 0 THEN 'Solicitada manifestação técnica complementar.'
        WHEN 1 THEN 'Realizada consulta a órgão externo competente.'
        WHEN 2 THEN 'Verificados aspectos normativos específicos.'
        WHEN 3 THEN 'Efetuada análise de impacto e viabilidade.'
        WHEN 4 THEN 'Concluída avaliação de conformidade técnica.'
        ELSE 'Programada vistoria para validação final.'
    END,
    p.DT_ABERTURA + INTERVAL '15 days',
    ((p.CO_SEQ_PROCESSO - 1) % 120) + 1,
    'S'
FROM TB_PROCESSO p
WHERE p.CO_SITUACAO_PROCESSO = 'EM_TRAMITACAO';

-- ==================================================================
-- TRAMITAÇÕES (500+ registros, 2-4 por processo)
-- ==================================================================

-- Primeira tramitação: protocolo -> setor específico
INSERT INTO TH_TRAMITACAO (
    CO_SEQ_TRAMITACAO, CO_SEQ_PROCESSO, CO_SEQ_SETOR_ORIGEM,
    CO_SEQ_SETOR_DESTINO, CO_SEQ_ANALISTA, DT_TRAMITACAO,
    CO_SEQ_USUARIO, DS_OBSERVACAO
)
SELECT
    i,
    i,
    NULL,  -- Origem externa (protocolo)
    CASE i % 10
        WHEN 0 THEN 20  -- Licenciamento
        WHEN 1 THEN 2   -- Jurídico
        WHEN 2 THEN 9   -- Obras
        WHEN 3 THEN 17  -- Tributação
        WHEN 4 THEN 10  -- Meio Ambiente
        WHEN 5 THEN 19  -- Fiscalização
        WHEN 6 THEN 16  -- Planejamento
        WHEN 7 THEN 3   -- Administração
        WHEN 8 THEN 7   -- TI
        ELSE 21         -- Urbanismo
    END,
    NULL,  -- Será atualizado posteriormente
    (SELECT DT_ABERTURA FROM TB_PROCESSO WHERE CO_SEQ_PROCESSO = i),
    ((i - 1) % 120) + 1,
    'Processo recebido no protocolo e distribuído para análise inicial.'
FROM generate_series(1, 200) AS i;

-- Atualizar tramitações com analistas corretos
UPDATE TH_TRAMITACAO
SET CO_SEQ_ANALISTA = (
    SELECT MIN(a.CO_SEQ_ANALISTA)
    FROM TB_ANALISTA a
    WHERE a.CO_SEQ_SETOR = TH_TRAMITACAO.CO_SEQ_SETOR_DESTINO
)
WHERE CO_SEQ_TRAMITACAO <= 200;

-- Segunda tramitação: entre setores (para 80% dos processos)
INSERT INTO TH_TRAMITACAO (
    CO_SEQ_TRAMITACAO, CO_SEQ_PROCESSO, CO_SEQ_SETOR_ORIGEM,
    CO_SEQ_SETOR_DESTINO, CO_SEQ_ANALISTA, DT_TRAMITACAO,
    CO_SEQ_USUARIO, DS_OBSERVACAO
)
SELECT
    200 + ROW_NUMBER() OVER (ORDER BY i),
    i,
    t.CO_SEQ_SETOR_DESTINO,  -- Origem = destino da tramitação anterior
    CASE
        WHEN t.CO_SEQ_SETOR_DESTINO IN (20, 9, 10) THEN 2  -- Licenc/Obras/Amb -> Jurídico
        WHEN t.CO_SEQ_SETOR_DESTINO = 2 THEN 3             -- Jurídico -> Administração
        WHEN t.CO_SEQ_SETOR_DESTINO IN (17, 19) THEN 5     -- Trib/Fisc -> Financeiro
        WHEN t.CO_SEQ_SETOR_DESTINO = 16 THEN 21           -- Planej -> Urbanismo
        WHEN t.CO_SEQ_SETOR_DESTINO = 21 THEN 9            -- Urban -> Obras
        ELSE 3  -- Demais -> Administração
    END,
    NULL,
    t.DT_TRAMITACAO + INTERVAL '2 days',
    ((i - 1) % 120) + 1,
    'Processo analisado e encaminhado para continuidade da análise técnica.'
FROM generate_series(1, 200) AS i
JOIN TH_TRAMITACAO t ON t.CO_SEQ_PROCESSO = i AND t.CO_SEQ_TRAMITACAO = i
WHERE i % 5 != 0;  -- 80% dos processos

-- Atualizar segunda tramitação com analistas
UPDATE TH_TRAMITACAO
SET CO_SEQ_ANALISTA = (
    SELECT MIN(a.CO_SEQ_ANALISTA)
    FROM TB_ANALISTA a
    WHERE a.CO_SEQ_SETOR = TH_TRAMITACAO.CO_SEQ_SETOR_DESTINO
)
WHERE CO_SEQ_TRAMITACAO > 200;

-- Terceira tramitação: finalização (para processos encerrados)
INSERT INTO TH_TRAMITACAO (
    CO_SEQ_TRAMITACAO, CO_SEQ_PROCESSO, CO_SEQ_SETOR_ORIGEM,
    CO_SEQ_SETOR_DESTINO, CO_SEQ_ANALISTA, DT_TRAMITACAO,
    CO_SEQ_USUARIO, DS_OBSERVACAO
)
SELECT
    400 + ROW_NUMBER() OVER (ORDER BY p.CO_SEQ_PROCESSO),
    p.CO_SEQ_PROCESSO,
    3,  -- Administração como origem (decisão final)
    24, -- Arquivo como destino
    (SELECT MIN(CO_SEQ_ANALISTA) FROM TB_ANALISTA WHERE CO_SEQ_SETOR = 24),
    p.DT_ABERTURA + INTERVAL '20 days',
    ((p.CO_SEQ_PROCESSO - 1) % 120) + 1,
    'Processo finalizado e encaminhado para arquivo definitivo.'
FROM TB_PROCESSO p
WHERE p.CO_SITUACAO_PROCESSO = 'ENCERRADO';

-- ==================================================================
-- AJUSTE DAS SEQUENCES PARA PRÓXIMOS VALORES
-- ==================================================================

SELECT SETVAL('SQ_TB_PESSOA_FISICA_CO_SEQ', 200);
SELECT SETVAL('SQ_TB_PESSOA_JURIDICA_CO_SEQ', 100);
SELECT SETVAL('SQ_TB_SETOR_CO_SEQ', 25);
SELECT SETVAL('SQ_TB_USUARIO_CO_SEQ', 120);
SELECT SETVAL('SQ_TB_ANALISTA_CO_SEQ', 100);
SELECT SETVAL('SQ_TB_PROCESSO_CO_SEQ', 200);
SELECT SETVAL('SQ_RL_PROCESSO_PARTE_CO_SEQ', (SELECT COALESCE(MAX(CO_SEQ_PARTE), 0) FROM RL_PROCESSO_PARTE));
SELECT SETVAL('SQ_TB_REGISTRO_CO_SEQ', (SELECT COALESCE(MAX(CO_SEQ_REGISTRO), 0) FROM TB_REGISTRO));
SELECT SETVAL('SQ_TH_TRAMITACAO_CO_SEQ', (SELECT COALESCE(MAX(CO_SEQ_TRAMITACAO), 0) FROM TH_TRAMITACAO));

COMMIT;

-- ==================================================================
-- VERIFICAÇÃO DOS DADOS INSERIDOS
-- ==================================================================

SELECT 'TB_SITUACAO_PROCESSO' AS TABELA, COUNT(*) AS REGISTROS FROM TB_SITUACAO_PROCESSO
UNION ALL
SELECT 'TB_TIPO_PARTE' AS TABELA, COUNT(*) AS REGISTROS FROM TB_TIPO_PARTE
UNION ALL
SELECT 'TB_PESSOA_FISICA' AS TABELA, COUNT(*) AS REGISTROS FROM TB_PESSOA_FISICA
UNION ALL
SELECT 'TB_PESSOA_JURIDICA' AS TABELA, COUNT(*) AS REGISTROS FROM TB_PESSOA_JURIDICA
UNION ALL
SELECT 'TB_SETOR' AS TABELA, COUNT(*) AS REGISTROS FROM TB_SETOR
UNION ALL
SELECT 'TB_USUARIO' AS TABELA, COUNT(*) AS REGISTROS FROM TB_USUARIO
UNION ALL
SELECT 'TB_ANALISTA' AS TABELA, COUNT(*) AS REGISTROS FROM TB_ANALISTA
UNION ALL
SELECT 'TB_PROCESSO' AS TABELA, COUNT(*) AS REGISTROS FROM TB_PROCESSO
UNION ALL
SELECT 'RL_PROCESSO_PARTE' AS TABELA, COUNT(*) AS REGISTROS FROM RL_PROCESSO_PARTE
UNION ALL
SELECT 'TB_REGISTRO' AS TABELA, COUNT(*) AS REGISTROS FROM TB_REGISTRO
UNION ALL
SELECT 'TH_TRAMITACAO' AS TABELA, COUNT(*) AS REGISTROS FROM TH_TRAMITACAO
ORDER BY TABELA;

-- Resultado da inserção dos dados de teste
SELECT 'Dados de teste MASSIVOS inseridos com sucesso!' AS RESULTADO;