package graphql;

import graphql.collect.ImmutableMapWithNullValues;
import graphql.execution.MergedField;
import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLType;

import java.util.Map;

/**
 * This is passed to a {@link graphql.schema.TypeResolver} to help with object type resolution.
 *
 * See {@link graphql.schema.TypeResolver#getType} for how this is used
 */
@SuppressWarnings("TypeParameterUnusedInFormals")
@PublicApi
public class TypeResolutionEnvironment {

    private final Object object;
    private final ImmutableMapWithNullValues<String, Object> arguments;
    private final MergedField field;
    private final GraphQLType fieldType;
    private final GraphQLSchema schema;
    private final Object context;
    private final GraphQLContext graphQLContext;
    private final DataFetchingFieldSelectionSet fieldSelectionSet;

    public TypeResolutionEnvironment(Object object,
                                     Map<String, Object> arguments,
                                     MergedField field,
                                     GraphQLType fieldType,
                                     GraphQLSchema schema,
                                     Object context,
                                     GraphQLContext graphQLContext,
                                     DataFetchingFieldSelectionSet fieldSelectionSet) {
        this.object = object;
        this.arguments = ImmutableMapWithNullValues.copyOf(arguments);
        this.field = field;
        this.fieldType = fieldType;
        this.schema = schema;
        this.context = context;
        this.graphQLContext = graphQLContext;
        this.fieldSelectionSet = fieldSelectionSet;
    }


    /**
     * You will be passed the specific source object that needs to be resolved into a concrete graphql object type
     *
     * @param <T> you decide what type it is
     *
     * @return the object that needs to be resolved into a specific graphql object type
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject() {
        return (T) object;
    }

    /**
     * @return the runtime arguments to this the graphql field
     */
    public Map<String, Object> getArguments() {
        return arguments;
    }

    /**
     * @return the graphql field in question
     */
    public MergedField getField() {
        return field;
    }

    /**
     * @return the type of the graphql field, which still be either a {@link graphql.schema.GraphQLUnionType} or a
     * {@link graphql.schema.GraphQLInterfaceType}
     */
    public GraphQLType getFieldType() {
        return fieldType;
    }

    /**
     * @return the graphql schema in question
     */
    public GraphQLSchema getSchema() {
        return schema;
    }

    /**
     * Returns the context object set in via {@link ExecutionInput#getContext()}
     *
     * @param <T> to two
     *
     * @return the context object
     *
     * @deprecated use {@link #getGraphQLContext()} instead
     */
    @Deprecated
    public <T> T getContext() {
        return (T) context;
    }

    /**
     * @return the {@link GraphQLContext} object set in via {@link ExecutionInput#getGraphQLContext()}
     */
    public GraphQLContext getGraphQLContext() {
        return graphQLContext;
    }

    /**
     * @return the {@link DataFetchingFieldSelectionSet} for the current field fetch that needs type resolution
     */
    public DataFetchingFieldSelectionSet getSelectionSet() {
        return fieldSelectionSet;
    }
}
