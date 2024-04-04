package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.*;
import star.tratto.oraclegrammar.custom.Parser;
import star.tratto.oraclegrammar.custom.Splitter;
import star.tratto.util.FileUtils;
import star.tratto.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static star.tratto.data.OracleDP2TokenDPs.oracleDatapointToTokenDatapoints;
import static star.tratto.util.FileUtils.deleteDirectory;

public class TokensDataset {

    private static final Logger logger = LoggerFactory.getLogger(TokensDataset.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Parser parser = Parser.getInstance();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String TOKENS_DATASET_FOLDER = "src/main/resources/new-tokens-dataset/";
    public static TokenDPType DATASET_TYPE = TokenDPType.TOKEN;
    private static int fileIndex = 0;

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        logger.info("Dataset type: {}", DATASET_TYPE);
        Path tokensDatasetFolder = Paths.get(TOKENS_DATASET_FOLDER);
        FileUtils.deleteDirectory(tokensDatasetFolder);
        Files.createDirectories(tokensDatasetFolder);
        Path oraclesDatasetPath = Path.of(ORACLES_DATASET_FOLDER);
        DirectoryStream<Path> oraclesDatasetStream = Files.newDirectoryStream(oraclesDatasetPath);
        // Assume that only dataset files are in the folder
        for (Path oraclesDatasetFile : oraclesDatasetStream) {
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesDatasetFile.getFileName());
            logger.info("------------------------------------------------------------");
            Path tokensDatasetFile = Paths.get(TOKENS_DATASET_FOLDER + fileIndex++ + "_" + oraclesDatasetFile.getFileName());
            OutputStream tokensDatasetOutputStream = Files.newOutputStream(tokensDatasetFile);

            tokensDatasetOutputStream.write("[".getBytes());
            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile.toFile(), List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                logger.info("Processing oracle: {}", oracleDatapoint.getOracle());
                if (!StringUtils.compactExpression(oracleDatapoint.getOracle()).equals(StringUtils.compactExpression(Splitter.split(parser.parseOracle(oracleDatapoint.getOracle()))))) {
                    throw new RuntimeException("Oracle " + oracleDatapoint.getOracle() + " does not match the parsed oracle " + Splitter.split(parser.parseOracle(oracleDatapoint.getOracle())));
                }
                List<TokenDatapoint> tokenDatapoints;
                try {
                    tokenDatapoints = oracleDatapointToTokenDatapoints(oracleDatapoint);
                } catch (MissingTokenException e) {
                    logger.error(e.getMessage());
                    continue;
                }

                for (TokenDatapoint tokenDatapoint : tokenDatapoints) {
                    if (Files.size(tokensDatasetFile) / 1000 / 1000 > 48) { // Limit file size to ~50 MB
                        tokensDatasetOutputStream.write("]".getBytes());
                        tokensDatasetOutputStream.close();
                        tokensDatasetFile = Paths.get(TOKENS_DATASET_FOLDER + fileIndex++ + "_" + oraclesDatasetFile.getFileName());
                        tokensDatasetOutputStream = Files.newOutputStream(tokensDatasetFile);
                        tokensDatasetOutputStream.write("[".getBytes());
                    } else if (Files.size(tokensDatasetFile) > 1) { // size=1 means file only with "["
                        tokensDatasetOutputStream.write(",".getBytes());
                    }
                    tokensDatasetOutputStream.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(tokenDatapoint));
                }
            }
            tokensDatasetOutputStream.write("]".getBytes());
            tokensDatasetOutputStream.close();
        }

        logger.info("------------------------------------------------------------");
        logger.info("Finished generating tokens dataset.");
        logger.info("Total samples: {}", OracleDP2TokenDPs.getTokenIndex());
        logger.info("------------------------------------------------------------");
    }

    private static void validateArgs(String[] args) {
        if (args.length == 0 && DATASET_TYPE == null) {
            logger.error("DATASET_TYPE not set. Pass one argument, which must be one of TOKENS, TOKEN_CLASSES or TOKEN_VALUES.");
            System.exit(1);
        } else if (args.length > 1) {
            logger.error("Wrong number of arguments. Expected 0 or 1, got {}", args.length);
            System.exit(1);
        } else if (args.length == 1 && !Arrays.stream(TokenDPType.values()).map(Enum::name).toList().contains(args[0])) {
            logger.error("Wrong argument. Expected TOKENS or TOKEN_CLASSES or TOKEN_VALUES, got {}", args[0]);
            System.exit(1);
        }
        if (args.length == 1) {
            DATASET_TYPE = TokenDPType.valueOf(args[0]);
        }
    }
}
