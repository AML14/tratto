
import Code from "./Code.jsx";
import List from "./List.jsx";

export default function Main({ jdc, pre, post, throws, onClickCallback, deleteCallback }) {
    return (
        <>
            {
                jdc !== null ?
                (
                    <>
                        <div id="jdoctor-source">
                            <Code
                                label="Class Source Code"
                                identifier="class-source-code"
                                code={jdc.source.classSourceCode}
                                language="java"
                            />
                            <Code
                                label="Method Source Code"
                                identifier="method-source-code"
                                code={jdc.source.methodSourceCode}
                                language="java"
                            />
                            <Code
                                label="Javadoc"
                                identifier="javadoc"
                                code={jdc.source.javadoc}
                                language="java"
                            />
                        </div>
                        <div id="jdoctor-conditions">
                            <List
                                label="Pre Conditions"
                                identifier="pre"
                                elements={ pre.map( p => { return { name : p.guard.condition } } ) }
                                onClickCallback={ onClickCallback }
                                deleteButtonCallback={ deleteCallback.bind(null, "pre") }
                            />
                            <List
                                label="Post Conditions"
                                identifier="post"
                                elements={ post.map( p => { return { name : p.property.condition } } ) }
                                onClickCallback={ onClickCallback }
                                deleteButtonCallback={ deleteCallback.bind(null, "post") }
                            />
                            <List
                                label="Throws Conditions"
                                identifier="throws"
                                elements={ throws.map( t => { return { name : t.guard.condition } } ) }
                                onClickCallback={ onClickCallback }
                                deleteButtonCallback={ deleteCallback.bind(null, "throws") }
                            />
                        </div>
                    </>
                )
                :
                    <span>JDoctor Condition Not Found</span>
            }
        </>
    )
}