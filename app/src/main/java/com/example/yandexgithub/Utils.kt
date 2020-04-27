package com.example.yandexgithub

import android.graphics.Color
import org.json.JSONObject


// Convert programming language name to color (as in github website)
fun languageToColor(language: String): Int {
    return if (jsonColors.isNull(language)){
        // If there is no such language in JSON use C++ color as default
        Color.parseColor(jsonColors.getString("C++"))
    } else {
        Color.parseColor(jsonColors.getString(language))
    }
}


/**
 * This is very naive, temporary solution to store data about language colors
 * Should be replaced with SQLite or some file in resources of the app
 */
val jsonColors = JSONObject(
    "{\n" +
            "    \"Mercury\": \"#ff2b2b\", \n" +
            "    \"TypeScript\": \"#2b7489\", \n" +
            "    \"PureBasic\": \"#5a6986\", \n" +
            "    \"Objective-C++\": \"#6866fb\", \n" +
            "    \"Self\": \"#0579aa\", \n" +
            "    \"NewLisp\": \"#87AED7\", \n" +
            "    \"Fortran\": \"#4d41b1\", \n" +
            "    \"Ceylon\": \"#dfa535\", \n" +
            "    \"Rebol\": \"#358a5b\", \n" +
            "    \"Frege\": \"#00cafe\", \n" +
            "    \"AspectJ\": \"#a957b0\", \n" +
            "    \"Omgrofl\": \"#cabbff\", \n" +
            "    \"HolyC\": \"#ffefaf\", \n" +
            "    \"Shell\": \"#89e051\", \n" +
            "    \"HiveQL\": \"#dce200\", \n" +
            "    \"AppleScript\": \"#101F1F\", \n" +
            "    \"Eiffel\": \"#946d57\", \n" +
            "    \"XQuery\": \"#5232e7\", \n" +
            "    \"RUNOFF\": \"#665a4e\", \n" +
            "    \"RAML\": \"#77d9fb\", \n" +
            "    \"MTML\": \"#b7e1f4\", \n" +
            "    \"Elixir\": \"#6e4a7e\", \n" +
            "    \"SAS\": \"#B34936\", \n" +
            "    \"MQL4\": \"#62A8D6\", \n" +
            "    \"MQL5\": \"#4A76B8\", \n" +
            "    \"Agda\": \"#315665\", \n" +
            "    \"wisp\": \"#7582D1\", \n" +
            "    \"Dockerfile\": \"#384d54\", \n" +
            "    \"SRecode Template\": \"#348a34\", \n" +
            "    \"D\": \"#ba595e\", \n" +
            "    \"PowerBuilder\": \"#8f0f8d\", \n" +
            "    \"Kotlin\": \"#F18E33\", \n" +
            "    \"Opal\": \"#f7ede0\", \n" +
            "    \"TI Program\": \"#A0AA87\", \n" +
            "    \"Crystal\": \"#000100\", \n" +
            "    \"Objective-C\": \"#438eff\", \n" +
            "    \"Batchfile\": \"#C1F12E\", \n" +
            "    \"Oz\": \"#fab738\", \n" +
            "    \"Mirah\": \"#c7a938\", \n" +
            "    \"ZIL\": \"#dc75e5\", \n" +
            "    \"Objective-J\": \"#ff0c5a\", \n" +
            "    \"ANTLR\": \"#9DC3FF\", \n" +
            "    \"Roff\": \"#ecdebe\", \n" +
            "    \"Ragel\": \"#9d5200\", \n" +
            "    \"FreeMarker\": \"#0050b2\", \n" +
            "    \"Gosu\": \"#82937f\", \n" +
            "    \"Zig\": \"#ec915c\", \n" +
            "    \"Ruby\": \"#701516\", \n" +
            "    \"Nemerle\": \"#3d3c6e\", \n" +
            "    \"Jupyter Notebook\": \"#DA5B0B\", \n" +
            "    \"Component Pascal\": \"#B0CE4E\", \n" +
            "    \"Nextflow\": \"#3ac486\", \n" +
            "    \"Brainfuck\": \"#2F2530\", \n" +
            "    \"SystemVerilog\": \"#DAE1C2\", \n" +
            "    \"APL\": \"#5A8164\", \n" +
            "    \"Hack\": \"#878787\", \n" +
            "    \"Go\": \"#00ADD8\", \n" +
            "    \"Ring\": \"#2D54CB\", \n" +
            "    \"PHP\": \"#4F5D95\", \n" +
            "    \"Cirru\": \"#ccccff\", \n" +
            "    \"SQF\": \"#3F3F3F\", \n" +
            "    \"ZAP\": \"#0d665e\", \n" +
            "    \"Glyph\": \"#c1ac7f\", \n" +
            "    \"1C Enterprise\": \"#814CCC\", \n" +
            "    \"WebAssembly\": \"#04133b\", \n" +
            "    \"Java\": \"#b07219\", \n" +
            "    \"MAXScript\": \"#00a6a6\", \n" +
            "    \"Scala\": \"#c22d40\", \n" +
            "    \"Makefile\": \"#427819\", \n" +
            "    \"Perl\": \"#0298c3\", \n" +
            "    \"Jsonnet\": \"#0064bd\", \n" +
            "    \"Arc\": \"#aa2afe\", \n" +
            "    \"LLVM\": \"#185619\", \n" +
            "    \"GDScript\": \"#355570\", \n" +
            "    \"Verilog\": \"#b2b7f8\", \n" +
            "    \"Factor\": \"#636746\", \n" +
            "    \"Haxe\": \"#df7900\", \n" +
            "    \"Forth\": \"#341708\", \n" +
            "    \"Red\": \"#f50000\", \n" +
            "    \"YARA\": \"#220000\", \n" +
            "    \"Hy\": \"#7790B2\", \n" +
            "    \"mcfunction\": \"#E22837\", \n" +
            "    \"Volt\": \"#1F1F1F\", \n" +
            "    \"AngelScript\": \"#C7D7DC\", \n" +
            "    \"LSL\": \"#3d9970\", \n" +
            "    \"eC\": \"#913960\", \n" +
            "    \"Terra\": \"#00004c\", \n" +
            "    \"CoffeeScript\": \"#244776\", \n" +
            "    \"HTML\": \"#e34c26\", \n" +
            "    \"Lex\": \"#DBCA00\", \n" +
            "    \"UnrealScript\": \"#a54c4d\", \n" +
            "    \"Idris\": \"#b30000\", \n" +
            "    \"Swift\": \"#ffac45\", \n" +
            "    \"Modula-3\": \"#223388\", \n" +
            "    \"C\": \"#555555\", \n" +
            "    \"AutoHotkey\": \"#6594b9\", \n" +
            "    \"P4\": \"#7055b5\", \n" +
            "    \"Isabelle\": \"#FEFE00\", \n" +
            "    \"G-code\": \"#D08CF2\", \n" +
            "    \"Metal\": \"#8f14e9\", \n" +
            "    \"Clarion\": \"#db901e\", \n" +
            "    \"Vue\": \"#2c3e50\", \n" +
            "    \"JSONiq\": \"#40d47e\", \n" +
            "    \"Boo\": \"#d4bec1\", \n" +
            "    \"AutoIt\": \"#1C3552\", \n" +
            "    \"Genie\": \"#fb855d\", \n" +
            "    \"Clojure\": \"#db5855\", \n" +
            "    \"EQ\": \"#a78649\", \n" +
            "    \"Visual Basic\": \"#945db7\", \n" +
            "    \"CSS\": \"#563d7c\", \n" +
            "    \"Prolog\": \"#74283c\", \n" +
            "    \"SourcePawn\": \"#5c7611\", \n" +
            "    \"AMPL\": \"#E6EFBB\", \n" +
            "    \"Shen\": \"#120F14\", \n" +
            "    \"wdl\": \"#42f1f4\", \n" +
            "    \"Harbour\": \"#0e60e3\", \n" +
            "    \"Yacc\": \"#4B6C4B\", \n" +
            "    \"Tcl\": \"#e4cc98\", \n" +
            "    \"Quake\": \"#882233\", \n" +
            "    \"BlitzMax\": \"#cd6400\", \n" +
            "    \"PigLatin\": \"#fcd7de\", \n" +
            "    \"xBase\": \"#403a40\", \n" +
            "    \"Lasso\": \"#999999\", \n" +
            "    \"Processing\": \"#0096D8\", \n" +
            "    \"VHDL\": \"#adb2cb\", \n" +
            "    \"Elm\": \"#60B5CC\", \n" +
            "    \"Dhall\": \"#dfafff\", \n" +
            "    \"Propeller Spin\": \"#7fa2a7\", \n" +
            "    \"Rascal\": \"#fffaa0\", \n" +
            "    \"Alloy\": \"#64C800\", \n" +
            "    \"IDL\": \"#a3522f\", \n" +
            "    \"Slice\": \"#003fa2\", \n" +
            "    \"YASnippet\": \"#32AB90\", \n" +
            "    \"ATS\": \"#1ac620\", \n" +
            "    \"Ada\": \"#02f88c\", \n" +
            "    \"Nu\": \"#c9df40\", \n" +
            "    \"LFE\": \"#4C3023\", \n" +
            "    \"SuperCollider\": \"#46390b\", \n" +
            "    \"Oxygene\": \"#cdd0e3\", \n" +
            "    \"ASP\": \"#6a40fd\", \n" +
            "    \"Assembly\": \"#6E4C13\", \n" +
            "    \"Gnuplot\": \"#f0a9f0\", \n" +
            "    \"FLUX\": \"#88ccff\", \n" +
            "    \"C#\": \"#178600\", \n" +
            "    \"Turing\": \"#cf142b\", \n" +
            "    \"Vala\": \"#fbe5cd\", \n" +
            "    \"ECL\": \"#8a1267\", \n" +
            "    \"ObjectScript\": \"#424893\", \n" +
            "    \"NetLinx\": \"#0aa0ff\", \n" +
            "    \"Perl 6\": \"#0000fb\", \n" +
            "    \"MATLAB\": \"#e16737\", \n" +
            "    \"Emacs Lisp\": \"#c065db\", \n" +
            "    \"Stan\": \"#b2011d\", \n" +
            "    \"SaltStack\": \"#646464\", \n" +
            "    \"Gherkin\": \"#5B2063\", \n" +
            "    \"QML\": \"#44a51c\", \n" +
            "    \"Pike\": \"#005390\", \n" +
            "    \"DataWeave\": \"#003a52\", \n" +
            "    \"LOLCODE\": \"#cc9900\", \n" +
            "    \"ooc\": \"#b0b77e\", \n" +
            "    \"XSLT\": \"#EB8CEB\", \n" +
            "    \"XC\": \"#99DA07\", \n" +
            "    \"J\": \"#9EEDFF\", \n" +
            "    \"Mask\": \"#f97732\", \n" +
            "    \"EmberScript\": \"#FFF4F3\", \n" +
            "    \"TeX\": \"#3D6117\", \n" +
            "    \"Pep8\": \"#C76F5B\", \n" +
            "    \"R\": \"#198CE7\", \n" +
            "    \"Cuda\": \"#3A4E3A\", \n" +
            "    \"KRL\": \"#28430A\", \n" +
            "    \"Vim script\": \"#199f4b\", \n" +
            "    \"Lua\": \"#000080\", \n" +
            "    \"Asymptote\": \"#4a0c0c\", \n" +
            "    \"Ren'Py\": \"#ff7f7f\", \n" +
            "    \"Golo\": \"#88562A\", \n" +
            "    \"PostScript\": \"#da291c\", \n" +
            "    \"Fancy\": \"#7b9db4\", \n" +
            "    \"OCaml\": \"#3be133\", \n" +
            "    \"ColdFusion\": \"#ed2cd6\", \n" +
            "    \"Pascal\": \"#E3F171\", \n" +
            "    \"F#\": \"#b845fc\", \n" +
            "    \"API Blueprint\": \"#2ACCA8\", \n" +
            "    \"ActionScript\": \"#882B0F\", \n" +
            "    \"F*\": \"#572e30\", \n" +
            "    \"Fantom\": \"#14253c\", \n" +
            "    \"Zephir\": \"#118f9e\", \n" +
            "    \"Click\": \"#E4E6F3\", \n" +
            "    \"Smalltalk\": \"#596706\", \n" +
            "    \"Ballerina\": \"#FF5000\", \n" +
            "    \"DM\": \"#447265\", \n" +
            "    \"Ioke\": \"#078193\", \n" +
            "    \"PogoScript\": \"#d80074\", \n" +
            "    \"LiveScript\": \"#499886\", \n" +
            "    \"JavaScript\": \"#f1e05a\", \n" +
            "    \"Wollok\": \"#a23738\", \n" +
            "    \"Rust\": \"#dea584\", \n" +
            "    \"ABAP\": \"#E8274B\", \n" +
            "    \"ZenScript\": \"#00BCD1\", \n" +
            "    \"Slash\": \"#007eff\", \n" +
            "    \"Erlang\": \"#B83998\", \n" +
            "    \"Pan\": \"#cc0000\", \n" +
            "    \"LookML\": \"#652B81\", \n" +
            "    \"Scheme\": \"#1e4aec\", \n" +
            "    \"Squirrel\": \"#800000\", \n" +
            "    \"Nim\": \"#37775b\", \n" +
            "    \"Python\": \"#3572A5\", \n" +
            "    \"Max\": \"#c4a79c\", \n" +
            "    \"Solidity\": \"#AA6746\", \n" +
            "    \"Common Lisp\": \"#3fb68b\", \n" +
            "    \"Dart\": \"#00B4AB\", \n" +
            "    \"Nix\": \"#7e7eff\", \n" +
            "    \"Nearley\": \"#990000\", \n" +
            "    \"Nit\": \"#009917\", \n" +
            "    \"Chapel\": \"#8dc63f\", \n" +
            "    \"Groovy\": \"#e69f56\", \n" +
            "    \"Dylan\": \"#6c616e\", \n" +
            "    \"E\": \"#ccce35\", \n" +
            "    \"Parrot\": \"#f3ca0a\", \n" +
            "    \"Grammatical Framework\": \"#79aa7a\", \n" +
            "    \"Game Maker Language\": \"#71b417\", \n" +
            "    \"VCL\": \"#148AA8\", \n" +
            "    \"Papyrus\": \"#6600cc\", \n" +
            "    \"C++\": \"#f34b7d\", \n" +
            "    \"NetLinx+ERB\": \"#747faa\", \n" +
            "    \"Common Workflow Language\": \"#B5314C\", \n" +
            "    \"Clean\": \"#3F85AF\", \n" +
            "    \"X10\": \"#4B6BEF\", \n" +
            "    \"Puppet\": \"#302B6D\", \n" +
            "    \"Jolie\": \"#843179\", \n" +
            "    \"PLSQL\": \"#dad8d8\", \n" +
            "    \"sed\": \"#64b970\", \n" +
            "    \"Pawn\": \"#dbb284\", \n" +
            "    \"Standard ML\": \"#dc566d\", \n" +
            "    \"PureScript\": \"#1D222D\", \n" +
            "    \"Julia\": \"#a270ba\", \n" +
            "    \"nesC\": \"#94B0C7\", \n" +
            "    \"q\": \"#0040cd\", \n" +
            "    \"Haskell\": \"#5e5086\", \n" +
            "    \"NCL\": \"#28431f\", \n" +
            "    \"Io\": \"#a9188d\", \n" +
            "    \"Rouge\": \"#cc0088\", \n" +
            "    \"Racket\": \"#3c5caa\", \n" +
            "    \"NetLogo\": \"#ff6375\", \n" +
            "    \"AGS Script\": \"#B9D9FF\", \n" +
            "    \"Meson\": \"#007800\", \n" +
            "    \"Dogescript\": \"#cca760\", \n" +
            "    \"PowerShell\": \"#012456\"\n" +
            "}"
)