package star.tratto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static star.tratto.data.OracleDP2TokenDPs.oracleDatapointToTokenDatapoints;

public class TokensDataset {

    private static final Logger logger = LoggerFactory.getLogger(TokensDataset.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String ORACLES_DATASET_FOLDER = "src/main/resources/oracles-dataset/";
    public static String TOKENS_DATASET_FOLDER = "src/main/resources/tokens-dataset/";
    public static TokenDPType DATASET_TYPE = TokenDPType.TOKEN_CLASS;
    private static int fileIndex = 0;

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        logger.info("Dataset type: {}", DATASET_TYPE);
        File tokensDatasetFolder = new File(TOKENS_DATASET_FOLDER);
        FileUtils.deleteDirectory(tokensDatasetFolder);
        tokensDatasetFolder.mkdir();
        File[] oraclesDatasetFiles = new File(ORACLES_DATASET_FOLDER).listFiles();
        for (File oraclesDatasetFile : oraclesDatasetFiles) { // Assume that only dataset files are in the folder
            logger.info("------------------------------------------------------------");
            logger.info("Processing file: {}", oraclesDatasetFile.getName());
            logger.info("------------------------------------------------------------");
            File tokensDatasetFile = new File(TOKENS_DATASET_FOLDER + fileIndex++ + "_" + oraclesDatasetFile.getName());
            tokensDatasetFile.delete();
            FileOutputStream tokensDatasetOutputStream = new FileOutputStream(tokensDatasetFile, true);

            List<Map> rawOracleDatapoints = objectMapper.readValue(oraclesDatasetFile, List.class);
            for (Map rawOracleDatapoint : rawOracleDatapoints) {
                OracleDatapoint oracleDatapoint = new OracleDatapoint(rawOracleDatapoint);
                logger.info("Processing oracle: {}", oracleDatapoint.getOracle());
                List<TokenDatapoint> tokenDatapoints;
                try {
                    tokenDatapoints = oracleDatapointToTokenDatapoints(oracleDatapoint, DATASET_TYPE);
                } catch (MissingTokenException e) {
                    logger.error(e.getMessage());
                    continue;
                }

                for (TokenDatapoint tokenDatapoint : tokenDatapoints) {
                    if (tokensDatasetFile.length() == 0) {
                        tokensDatasetOutputStream.write("[".getBytes());
                    } else if (tokensDatasetFile.length() / 1000 / 1000 > 48) { // Limit file size to 50 MB
                        tokensDatasetOutputStream.write("]".getBytes());
                        tokensDatasetOutputStream.close();
                        tokensDatasetFile = new File(TOKENS_DATASET_FOLDER + fileIndex++ + "_" + oraclesDatasetFile.getName());
                        tokensDatasetFile.delete();
                        tokensDatasetOutputStream = new FileOutputStream(tokensDatasetFile, true);
                        tokensDatasetOutputStream.write("[".getBytes());
                    } else {
                        tokensDatasetOutputStream.write(",".getBytes());
                    }
                    tokensDatasetOutputStream.write(objectMapper.writeValueAsBytes(tokenDatapoint));
                }
            }
            tokensDatasetOutputStream.write("]".getBytes());
            tokensDatasetOutputStream.close();
        }

        logger.info("------------------------------------------------------------");
        logger.info("Finished generating tokens dataset.");
        logger.info("Total samples: {}", OracleDP2TokenDPs.positiveSamples + OracleDP2TokenDPs.negativeSamples);
        logger.info("Total positive samples: {}", OracleDP2TokenDPs.positiveSamples);
        logger.info("Total negative samples: {}", OracleDP2TokenDPs.negativeSamples);
        logger.info("------------------------------------------------------------");
    }

    private static void validateArgs(String[] args) {
        if (args.length == 0 && DATASET_TYPE == null) {
            logger.error("DATASET_TYPE not set. Pass one argument, which must be one of TOKENS, TOKEN_CLASSES or TOKEN_VALUES.");
        } else if (args.length > 1) {
            logger.error("Wrong number of arguments. Expected 0 or 1, got {}", args.length);
        } else if (args.length == 1 && !Arrays.stream(TokenDPType.values()).map(Enum::name).toList().contains(args[0])) {
            logger.error("Wrong argument. Expected TOKENS or TOKEN_CLASSES or TOKEN_VALUES, got {}", args[0]);
        } else {
            if (args.length == 1) DATASET_TYPE = TokenDPType.valueOf(args[0]);
            return;
        }
        System.exit(1);
    }
}
